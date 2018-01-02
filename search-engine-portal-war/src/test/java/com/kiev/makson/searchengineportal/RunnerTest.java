package com.kiev.makson.searchengineportal;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.nio.file.Path;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchEnginePortal.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class RunnerTest{

    protected static final String FILE_NAME_A = "testFileA.txt";
    protected static final String FILE_NAME_B = "testFileB.txt";
    protected static final String FILE_NAME_C = "testFileC.txt";
    protected static final String FILE_NAME_D = "testFileD.txt";

    protected static final String DOCUMENT_A = "far test sets rest tser ar ra na fa ha";
    protected static final String DOCUMENT_B = "fsf ytty raf 34 8/ far";
    protected static final String DOCUMENT_C = "ljl eses far 64 ]jfhh sc";
    protected static final String DOCUMENT_D = "lj3l es2es fa2r 643 ]jf4hh s2c raf";

    protected static final String SEARCH_VALUE_A = "far";
    protected static final String SEARCH_VALUE_B = "raf";
    protected static final String SEARCH_VALUE_C = "notPresent";

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    private MultipartFile getMultipartFile(final Path path) {
        final String name = FILE_NAME_A;
        final String originalFileName = FILE_NAME_A;
        final String contentType = "text/plain";
        byte[] content = DOCUMENT_A.getBytes();
        return new MockMultipartFile(name, originalFileName, contentType, content);
    }

    @PostConstruct
    public void init(){mockMvc  = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); }

}
