package br.com.gregory.apirest.controllers;

import br.com.gregory.apirest.controllers.dto.AddressRq;
import br.com.gregory.apirest.controllers.dto.AddressRs;
import br.com.gregory.apirest.controllers.services.GeocondigApi;
import br.com.gregory.apirest.models.AddressModel;
import br.com.gregory.apirest.repositories.AddressRepository;
import br.com.gregory.apirest.useful.StringUseful;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping("/")
    public List<AddressRs> findAll() {
        var address = addressRepository.findAll();
        return address.stream().map(AddressRs::convert).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AddressRs findById(@PathVariable("id") Long id) {
        var adrress = addressRepository.getOne(id);
        return AddressRs.convert(adrress);
    }

    @PostMapping("/")
    public void saveAdrress(@RequestBody AddressRq addressRq) throws IOException {
        var address = new AddressModel();
        address.setStreetName(addressRq.getStreetName());
        address.setNumber(addressRq.getNumber());
        address.setComplement(addressRq.getComplement());
        address.setNeighbourhood(addressRq.getNeighbourhood());
        address.setCity(addressRq.getCity());
        address.setState(addressRq.getState());
        address.setCountry(addressRq.getCountry());
        address.setZipcode(addressRq.getZipcode());
        address.setLatitude(addressRq.getLatitude());
        address.setLongitude(addressRq.getLongitude());

        if (StringUseful.isNullOrEmpty(address.getLatitude()) &&
                StringUseful.isNullOrEmpty(address.getLongitude())) {
            getGoogleApi(address);
        }

        addressRepository.save(address);
    }

    @PutMapping("/{id}")
    public void updateAddress(@PathVariable("id") Long id, @RequestBody AddressRq addressRq)
            throws Exception {
        var address = addressRepository.findById(id);

        if (address.isPresent()) {
            var addressChanged = address.get();
            addressChanged.setStreetName(addressRq.getStreetName());
            addressChanged.setNumber(addressRq.getNumber());
            addressChanged.setComplement(addressRq.getComplement());
            addressChanged.setNeighbourhood(addressRq.getNeighbourhood());
            addressChanged.setCity(addressRq.getCity());
            addressChanged.setState(addressRq.getState());
            addressChanged.setCountry(addressRq.getCountry());
            addressChanged.setZipcode(addressRq.getZipcode());
            addressChanged.setLatitude(addressRq.getLatitude());
            addressChanged.setLongitude(addressRq.getLongitude());
            addressRepository.save(addressChanged);
        } else {
            throw new Exception("Address not found!");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) throws Exception {
        var address = addressRepository.findById(id);

        if (address.isPresent()) {
            var addressDeleted = address.get();
            addressRepository.delete(addressDeleted);
        } else {
            throw new Exception("Address not found!");
        }
    }

    private void getGoogleApi(AddressModel address) throws IOException {
        String googleAdrressApi = GeocondigApi.GOOGLE_ADDRES_API;
        String googleKey = GeocondigApi.GOOGLE_API_KEY;
        String fullAdrressApi = googleAdrressApi + address.getZipcode() + googleKey;

        URL url = new URL(null, fullAdrressApi, new sun.net.www.protocol.https.Handler());
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setRequestMethod("GET");
        httpsURLConnection.setReadTimeout(60000);
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
        String inputLines;
        StringBuilder response = new StringBuilder();

        while ((inputLines = br.readLine()) != null)
            response.append(inputLines);
        String responseGoogleApi = response.toString();
        br.close();

        JsonObject responseJson = new JsonObject();
        responseJson.get(responseGoogleApi);
        JsonArray jsonArray = (JsonArray) responseJson.get("results");

        if (jsonArray.size() > 0) {
            String latitude = null;
            String longitude = null;
            JsonArray objJsonList = (JsonArray) ((JsonObject) jsonArray.get(0)).get("location");

            for (int i = 0; i < objJsonList.size(); i++) {
                latitude = ((JsonArray) ((JsonObject) objJsonList.get(i)).get("lat")).get(Integer.parseInt("value")).toString();
                longitude = ((JsonArray) ((JsonObject) objJsonList.get(i)).get("lng")).get(Integer.parseInt("value")).toString();
            }

            if (!StringUseful.isNullOrEmpty(latitude) && !StringUseful.isNullOrEmpty(longitude)) {
                address.setLatitude(Float.parseFloat(latitude));
                address.setLongitude(Float.parseFloat(longitude));
            }
        }
    }
}
