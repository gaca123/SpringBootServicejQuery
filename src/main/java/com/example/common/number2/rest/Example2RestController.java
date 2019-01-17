/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.common.number2.rest;

import com.example.common.number1.rest.*;
import com.example.common.number2.domen.Player;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.fasterxml.jackson.databind.ser.impl.AttributePropertyWriter;
import com.fasterxml.jackson.module.jsonSchema.customProperties.TitleSchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.types.ObjectSchema;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;
import com.github.reinert.jjschema.JsonSchemaGenerator;
import com.github.reinert.jjschema.SchemaGeneratorBuilder;
import com.github.reinert.jjschema.v1.JsonSchemaFactory;
import com.github.reinert.jjschema.v1.JsonSchemaV4Factory;
import com.google.common.collect.HashBiMap;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CECA
 */
@RestController
public class Example2RestController {

    private static ObjectMapper mapper = new ObjectMapper();
    public static final String JSON_$SCHEMA_DRAFT4_VALUE = "http://json-schema.org/draft-04/schema#";
    public static final String JSON_$SCHEMA_ELEMENT = "$schema";

    @RequestMapping(value = "/schemaExample2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Object get() {

        try {
            JsonSchemaFactory schemaFactory = new JsonSchemaV4Factory();
            schemaFactory.setAutoPutDollarSchema(true);
            JsonNode productSchema = schemaFactory.createSchema(Player.class);
            System.out.println(productSchema);

            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(productSchema));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unknown user.");
        }
    }

    @RequestMapping(value = "/formExample2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Object getForm() {

        try {
            ObjectMapper mapper1 = new ObjectMapper();
            ObjectNode form = mapper1.createObjectNode();
            ObjectNode fields = mapper1.createObjectNode();
            ObjectNode formIdPlayer = mapper1.createObjectNode();
            ObjectNode formHeight = mapper1.createObjectNode();
            ObjectNode formWeight = mapper1.createObjectNode();
            ObjectNode formName = mapper1.createObjectNode();
            ObjectNode formSurname = mapper1.createObjectNode();
            ObjectNode formButton = mapper1.createObjectNode();
            ObjectNode formTypeButton = mapper1.createObjectNode();
            ObjectNode formForButton = mapper1.createObjectNode();
            ObjectNode formDateOfBirth = mapper1.createObjectNode();
            ObjectNode formPlayerStatusRadio = mapper1.createObjectNode();
            ObjectNode formPlayerStatusSelect = mapper1.createObjectNode();
            ObjectNode formPlayerStatusCheck = mapper1.createObjectNode();
            ArrayNode optionLabels = mapper1.createArrayNode();
            ObjectNode formPlayerAttributes = mapper1.createObjectNode();
            ObjectNode formPlayerStatusTypeAhead = mapper1.createObjectNode();
            ObjectNode multiselect = mapper1.createObjectNode();
            ObjectNode datasets = mapper1.createObjectNode();
            
            optionLabels.add("Inactive");
            optionLabels.add("Active");
            optionLabels.add("Retirement");

            multiselect.put("enableFiltering", true);
            multiselect.put("includeSelectAllOption", true);
         
            formPlayerStatusTypeAhead.putPOJO("type", "select");
            formPlayerStatusTypeAhead.putPOJO("multiple", true);
            formPlayerStatusTypeAhead.putPOJO("order", 11);
            formPlayerStatusTypeAhead.putPOJO("multiselect", multiselect);
            
            
            formPlayerAttributes.put("type", "textarea");
            formPlayerAttributes.put("rows", 6);
            formPlayerAttributes.put("cols", 80);
            formPlayerAttributes.put("order", 10);
            
            formPlayerStatusRadio.put("type", "radio");
            formPlayerStatusRadio.putPOJO("optionLabels", optionLabels);
            formPlayerStatusRadio.put("order", 7);
            
   
            formPlayerStatusSelect.put("type", "select");
            formPlayerStatusSelect.putPOJO("optionLabels", optionLabels);
            formPlayerStatusSelect.put("order", 8);
            formPlayerStatusSelect.put("sort", "");
                       
            formPlayerStatusCheck.put("type", "checkbox");
            formPlayerStatusCheck.putPOJO("optionLabels", optionLabels);
            formPlayerStatusCheck.put("order", 9);
            
            formDateOfBirth.put("type", "date");
            formDateOfBirth.put("dateFormat", "DD/MM/YYYY");
            formDateOfBirth.put("order", 6);
            
            formIdPlayer.put("type", "integer");
            formIdPlayer.put("order", 1);
            formIdPlayer.put("placeholder", "Id player...");
            formIdPlayer.put("helper", "Insert id player!!!");
            formIdPlayer.put("label", "ID player");

            formHeight.put("order", 4);
            formHeight.put("placeholder", "Height player...");
            formHeight.put("helper", "Insert height player!!!");

            formWeight.put("order", 5);
            formWeight.put("placeholder", "Weight player...");
            formWeight.put("helper", "Insert weight player!!!");

            formName.put("type", "text");
            formName.put("order", 2);
            formName.put("placeholder", "Name player...");
            formName.put("helper", "Insert name player!!!");
            formName.put("constrainMaxLength", true);
            formName.put("constrainMinLength", true);
            formName.put("showMaxLengthIndicator", true);

            formSurname.put("type", "text");
            formSurname.put("order", 3);
            formSurname.put("placeholder", "Surname player...");
            formSurname.put("helper", "Insert surname player!!!");
            formSurname.putPOJO("disallowEmptySpaces", true);
            
            formTypeButton.put("value", "Check model");
            formTypeButton.put("click", "");
            formButton.putPOJO("check", formTypeButton);
            formForButton.putPOJO("buttons", formButton);

            fields.putPOJO("idPlayer", formIdPlayer);
            fields.putPOJO("height", formHeight);
            fields.putPOJO("weight", formWeight);
            fields.putPOJO("name", formName);
            fields.putPOJO("surname", formSurname);
            fields.putPOJO("dateOfBirth", formDateOfBirth);
            fields.putPOJO("playerStatusRadio", formPlayerStatusRadio);
            fields.putPOJO("playerStatusSelect", formPlayerStatusSelect);
            fields.putPOJO("playerStatusCheck", formPlayerStatusCheck);
            fields.putPOJO("playerAttributes", formPlayerAttributes);
            fields.putPOJO("playerStatusTypeAhead", formPlayerStatusTypeAhead);

            form.putPOJO("fields", fields);
            form.putPOJO("form", formForButton);
            return ResponseEntity.status(HttpStatus.OK).body(form);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unknown user.");
        }
    }

    @RequestMapping(value = "/modelExample2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Object getModel() {

        try {
            ObjectMapper mapper1 = new ObjectMapper();
            ObjectNode model= mapper1.createObjectNode();
            model.put("idPlayer", 1);
            model.put("playerStatusSelect", "active");
            return ResponseEntity.status(HttpStatus.OK).body(model);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unknown user.");
        }
    }

}
