package ch.mare.trainingplanner.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.javamoney.moneta.Money;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class MoneySerialization {

    public static class JsonSerialization extends JsonSerializer<Money> {
        @Override
        public void serialize(Money value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeNumberField("amount", value.getNumber().doubleValue());
            gen.writeStringField("currency", value.getCurrency().getCurrencyCode());
        }
    }

    public static class JsonDeserialization extends JsonDeserializer<Money> {
        @Override
        public Money deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            NumericNode amount = (NumericNode) p.getCodec().readTree(p).get("amount");
            TextNode currency = (TextNode) p.getCodec().readTree(p).get("currency");
            return Money.of(amount.asDouble(), currency.asText());
        }
    }
}
