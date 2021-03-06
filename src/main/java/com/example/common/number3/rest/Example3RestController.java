/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.common.number3.rest;

import com.example.common.number3.domen.Player;
import com.example.common.number3.domen.Team;
import com.example.common.number3.rest.*;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
public class Example3RestController {

    private static ObjectMapper mapper = new ObjectMapper();
    public static final String JSON_$SCHEMA_DRAFT4_VALUE = "http://json-schema.org/draft-04/schema#";
    public static final String JSON_$SCHEMA_ELEMENT = "$schema";

    @RequestMapping(value = "/schemaExample3", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    @RequestMapping(value = "/formExample3", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
            ArrayNode dataSource = mapper1.createArrayNode();
            ObjectNode formTeam = mapper1.createObjectNode();
            ObjectNode multiselect = mapper1.createObjectNode();

            List<Team> teams = loadListOfTeams();
            for (Team team : teams) {
                ObjectNode pon = mapper1.createObjectNode();
                if (team.getId() == 0) {
                    pon.putPOJO("value", null);
                    pon.putPOJO("text", null);
                } else {
                    pon.putPOJO("value", team.toString());
                    pon.put("text", team.getName());
                }

                dataSource.add(pon);
            }
            multiselect.put("enableFiltering", true);
            multiselect.put("includeSelectAllOption", true);
            formTeam.putPOJO("datasource", dataSource);
            formTeam.put("type", "select");
            formTeam.put("order", 7);
            formTeam.put("multiple", true);
            formTeam.putPOJO("multiselect", multiselect);

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
            fields.putPOJO("team", formTeam);

            form.putPOJO("fields", fields);
            form.putPOJO("form", formForButton);
            return ResponseEntity.status(HttpStatus.OK).body(form);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unknown user.");
        }
    }

    @RequestMapping(value = "/modelExample3", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Object getModel() {

        try {
            ObjectMapper mapper1 = new ObjectMapper();
            ObjectNode model = mapper1.createObjectNode();
            model.put("idPlayer", 1);
            return ResponseEntity.status(HttpStatus.OK).body(model);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unknown user.");
        }
    }

    private List<Team> loadListOfTeams() {
        List<Team> teams = new ArrayList<>();

        Team team2 = new Team();
        team2.setId(1);
        team2.setName("Barcelona");

        Team team3 = new Team();
        team3.setId(2);
        team3.setName("Real Madrid");

        Team team4 = new Team();
        team4.setId(3);
        team4.setName("Liverpool");

        Team team5 = new Team();
        team5.setId(4);
        team5.setName("Chelsea");

        Team team6 = new Team();
        team6.setId(5);
        team6.setName("Valencia");

        Team team7 = new Team();
        team7.setId(6);
        team7.setName("Inter");

        Team team8 = new Team();
        team8.setId(7);
        team8.setName("Lazio");

        teams.add(team2);
        teams.add(team3);
        teams.add(team4);
        teams.add(team5);
        teams.add(team6);
        teams.add(team7);
        teams.add(team8);

        Collections.sort(teams);
        return teams;
    }

}
