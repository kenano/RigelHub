package com.kenanozdamar.android.demo.rigelhub.fixtures;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.kenanozdamar.android.demo.services.githubclient.models.ClientResults;

import java.io.IOException;
import java.io.InputStream;

public class Fixtures {

    public ClientResults validResults() {
        ClientResults clientResults;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectReader reader = mapper.readerFor(ClientResults.class);
            clientResults = reader
                    .with(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
                    .with(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                    .readValue(stringForFile("apiResponse_results_found.txt"));
        } catch (Throwable error) {
            return null;
        }
        return clientResults;
    }

    public ClientResults emptyResults() {
        ClientResults clientResults;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectReader reader = mapper.readerFor(ClientResults.class);
            clientResults = reader
                    .with(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
                    .with(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                    .readValue(stringForFile("apiResponse_empty_results.txt"));
        } catch (Throwable error) {
            return null;
        }
        return clientResults;
    }

    public ClientResults threeResults() {
        ClientResults clientResults;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectReader reader = mapper.readerFor(ClientResults.class);
            clientResults = reader
                    .with(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
                    .with(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                    .readValue(stringForFile("apiResponse_three_results.txt"));
        } catch (Throwable error) {
            return null;
        }
        return clientResults;
    }

    public ClientResults twoResults() {
        ClientResults clientResults;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectReader reader = mapper.readerFor(ClientResults.class);
            clientResults = reader
                    .with(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
                    .with(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                    .readValue(stringForFile("apiResponse_two_results.txt"));
        } catch (Throwable error) {
            return null;
        }
        return clientResults;
    }

    public ClientResults twoResultsNullOwner() {
        ClientResults clientResults;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectReader reader = mapper.readerFor(ClientResults.class);
            clientResults = reader
                    .with(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
                    .with(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                    .readValue(stringForFile("apiResponse_two_results_null_owner.txt"));
        } catch (Throwable error) {
            return null;
        }
        return clientResults;
    }

    public ClientResults twoResultsNullLogin() {
        ClientResults clientResults;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectReader reader = mapper.readerFor(ClientResults.class);
            clientResults = reader
                    .with(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
                    .with(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                    .readValue(stringForFile("apiResponse_two_results_null_login.txt"));
        } catch (Throwable error) {
            return null;
        }
        return clientResults;
    }

    public ClientResults twoResultsEmptyHtmlUrl() {
        ClientResults clientResults;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectReader reader = mapper.readerFor(ClientResults.class);
            clientResults = reader
                    .with(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
                    .with(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                    .readValue(stringForFile("apiResponse_two_results_empty_html_url.txt"));
        } catch (Throwable error) {
            return null;
        }
        return clientResults;
    }

    public ClientResults twoResultsInvalidInteger() {
        ClientResults clientResults;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectReader reader = mapper.readerFor(ClientResults.class);
            clientResults = reader
                    .with(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
                    .with(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                    .readValue(stringForFile("apiResponse_two_results_invalid_integer.txt"));
        } catch (Throwable error) {
            return null;
        }
        return clientResults;
    }

    //region InputStream method(s)
    private String stringForFile(String filename) {
        String json;
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException error) {
            return null;
        }
        return json;
    }
    //end region
}
