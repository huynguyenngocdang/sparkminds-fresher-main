package com.sparkminds.fresher_project_backend.utility.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

public class CustomDoubleSerializer extends JsonSerializer<Double> {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeString(decimalFormat.format(value));
    }
}
