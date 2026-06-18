package com.raf.aplikacija.model;


import com.raf.aplikacija.restclient.dto.UserDto;
import com.raf.aplikacija.restclient.dto.UserListDto;

import javax.swing.table.DefaultTableModel;


public class UserTableModel extends DefaultTableModel {

    public UserTableModel() throws IllegalAccessException, NoSuchMethodException {
        super(new String[]{"Id korisnika", "Email","Ime","Prezime","Username","Datum rodjenja","Aktivan","Zabranjen"}, 0);
    }

    private UserListDto userListDto = new UserListDto();


    @Override
    public void addRow(Object[] row) {
        super.addRow(row);
        UserDto dto = new UserDto();

        dto.setId(Long.valueOf(String.valueOf(row[0])));
        dto.setEmail(String.valueOf(row[1]));
        dto.setFirstName(String.valueOf(row[2]));
        dto.setLastName(String.valueOf(row[3]));
        dto.setUsername(String.valueOf(row[4]));
        dto.setDateOfBirth(String.valueOf(row[5]));
        dto.setAktivan((Boolean) row[6]);
        dto.setZabranjen((Boolean) row[7]);

        userListDto.getContent().add(dto);
    }
}
