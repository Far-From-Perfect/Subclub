package com.example.subclub.untils;

import com.example.subclub.entity.Titles;
import com.example.subclub.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class TitleRowMapper implements RowMapper<Titles> {
    private final TypeRepository typeRepository;
    @Override
    public Titles mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Titles()
                .setId(rs.getLong("id"))
                .setTitle(rs.getString("title"))
                .setDescription(rs.getString("description"))
                .setMediaType(typeRepository.findByMediaTypename(rs.getString("name")).get());

    }
}
