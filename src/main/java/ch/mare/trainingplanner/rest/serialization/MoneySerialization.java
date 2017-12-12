package ch.mare.trainingplanner.rest.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
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
            gen.writeStartObject();
            gen.writeObjectField("amount", value.getNumber().doubleValue());
            gen.writeObjectField("currency", value.getCurrency().getCurrencyCode());
            gen.writeEndObject();
        }
    }

    public static class JsonDeserialization extends JsonDeserializer<Money> {
        @Override
        public Money deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            TreeNode treeNode = p.getCodec().readTree(p);
            NumericNode amount = (NumericNode) treeNode.get("amount");
            TextNode currency = (TextNode) treeNode.get("currency");
            return Money.of(amount.asDouble(), currency.asText());
        }
    }
}
