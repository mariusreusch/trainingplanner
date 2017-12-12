package ch.mare.trainingplanner.rest.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.ZonedDateTime;

import static java.time.ZoneId.systemDefault;
import static java.time.ZonedDateTime.parse;

@JsonComponent
public class ZonedDateTimeJsonDeserialization extends JsonDeserializer<ZonedDateTime> {

        @Override
        public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return parse(p.getValueAsString()).withZoneSameLocal(systemDefault());
        }
    }