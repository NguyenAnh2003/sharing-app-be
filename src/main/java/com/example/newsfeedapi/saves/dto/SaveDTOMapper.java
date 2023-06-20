package com.example.newsfeedapi.saves.dto;

import com.example.newsfeedapi.saves.Save;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class SaveDTOMapper implements Function<Save, SaveDTO> {
    @Override
    public SaveDTO apply(Save save) {
        return null;
    }
}
