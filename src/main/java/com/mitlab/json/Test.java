package com.mitlab.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.load.configuration.LoadingConfiguration;
import com.github.fge.jsonschema.core.load.configuration.LoadingConfigurationBuilder;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.examples.Utils;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class Test {
    public static void main(String[] args) throws Exception {
        final LoadingConfigurationBuilder builder  = LoadingConfiguration.newBuilder();
        final String URI_BASE = "";
        JsonNode node;
        String uri;

        node = Utils.loadResource("/split/fstab.json");
        uri = URI_BASE + "fstab.json";
        builder.preloadSchema(uri, node);

        node = Utils.loadResource("/split/mntent.json");
        uri = URI_BASE + "mntent.json";
        builder.preloadSchema(uri, node);

        final JsonSchemaFactory factory = JsonSchemaFactory.newBuilder()
                .setLoadingConfiguration(builder.freeze()).freeze();

        final JsonSchema schema
                = factory.getJsonSchema(URI_BASE + "fstab.json");

        final JsonNode good = Utils.loadResource("/fstab-good.json");
        final JsonNode bad = Utils.loadResource("/fstab-bad.json");
        final JsonNode bad2 = Utils.loadResource("/fstab-bad2.json");
        final JsonNode projects = Utils.loadResource("/project.json");

        ProcessingReport report;

        /*report = schema.validate(good);
        System.out.println(report);

        report = schema.validate(bad);
        System.out.println(report);

        report = schema.validate(bad2);
        System.out.println(report);*/
        
        report = schema.validate(projects);
        System.out.println(report);
    }
}
