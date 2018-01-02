package com.kiev.makson.searchengineportal.controller;

import com.kiev.makson.searchengineservice.model.dto.TokenDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.kiev.makson.searchengineportal.controller.SearchEnginePortalController.URL_SEARCH_ENGINE_PORTAL;

@ControllerAdvice
@RequestMapping(URL_SEARCH_ENGINE_PORTAL)
public class SearchEnginePortalController {

    public static final String URL_SEARCH_ENGINE_PORTAL = "/api/search-engine-portal";

    public static final String REQUEST_FILE_PARAM = "data";
    public static final String HEADER_KEY = "Content-Disposition";

    private final String url;
    private final String urn;

    private final RestTemplate restTemplate;

    public SearchEnginePortalController(final Environment env) {
        url = env.getProperty("searchEngineServiceSettings.url");
        urn = env.getProperty("searchEngineServiceSettings.urn");

        restTemplate = new RestTemplate();
    }

    @PostMapping
    public ResponseEntity<?> putDocument(@RequestParam(REQUEST_FILE_PARAM) final MultipartFile file) throws IOException {

        final MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add(REQUEST_FILE_PARAM, convertFile(file));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        final HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

        return restTemplate.exchange(url + urn,
                HttpMethod.POST, requestEntity, String.class);
    }

    @GetMapping("/{identificationKey}")
    public ResponseEntity<byte[]> getDocument(@PathVariable String identificationKey) {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());

        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));

        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange(url + urn + "/" + identificationKey, HttpMethod.GET, entity, byte[].class);
    }

    @PutMapping
    public ResponseEntity<?> searchDocumentIdentificationKey(@RequestBody final List<TokenDto> tokens) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<Object> requestEntity = new HttpEntity<Object>(tokens, headers);

        return restTemplate.exchange(url + urn, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<List<TokenDto>>() {
        });
    }

    private Resource convertFile(final MultipartFile file) throws IOException {
        final String tempFileName = "/tmp/" + file.getOriginalFilename();
        final FileOutputStream fo = new FileOutputStream(tempFileName);
        fo.write(file.getBytes());
        fo.close();
        return new FileSystemResource(tempFileName);
    }
}
