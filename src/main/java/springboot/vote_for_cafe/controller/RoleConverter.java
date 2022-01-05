package springboot.vote_for_cafe.controller;

import springboot.vote_for_cafe.model.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;



    //@Converter(autoApply = true)
    public class RoleConverter  {

       /* @Override
        public String convertToDatabaseColumn(Role role) {
            return role.getRoleName();
        }

        @Override
        public Role convertToEntityAttribute(String dbData) {
            return Role.getFromShortName(dbData);
        } */
    }
