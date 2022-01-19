package hexa.bank.account.bank.cucumber.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;

import java.lang.reflect.Type;

public class CucumberConfiguration {

    private final ObjectMapper objectMapper;

    public CucumberConfiguration() {
        this.objectMapper = new ObjectMapper().registerModule(new JSR310Module());
    }

    @DefaultDataTableCellTransformer
    @DefaultDataTableEntryTransformer
    @DefaultParameterTransformer
    public Object transform(final Object from, Type to) {
        return objectMapper.convertValue(from, objectMapper.constructType(to));
    }


}
