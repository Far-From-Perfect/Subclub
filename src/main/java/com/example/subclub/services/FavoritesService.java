package com.example.subclub.services;

import com.example.subclub.dto.TitleDTO;
import com.example.subclub.entity.Titles;
import com.example.subclub.untils.TitleMapper;
import com.example.subclub.untils.TitleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoritesService {

    private final JdbcTemplate jdbcTemplate;
    private final TitleMapper titleMapper;
    private final TitleRowMapper titleRowMapper;

    public Collection<TitleDTO> getFavorites(Long id) {
        List<Titles> requestToDb= jdbcTemplate.query("""
                select t.id, t.title, t.description, mt.name
                from (select title_id
                    from users_titles
                    where user_id=?) as sub_q
                    inner join titles as t
                        on t.id=sub_q.title_id
                    left join content_type 
                        on content_type.title_id=t.id
                    left join media_type as mt
                        on mt.id=content_type.media_type_id;
                """, titleRowMapper, id);

        return titleMapper.toDtoList(requestToDb);
    }

    public ResponseEntity<?> addToFavorites(Long id, TitleDTO titleDTO) {
        Long titleId = titleMapper.toEntity(titleDTO).getId();
        log.info(String.format("Trying to update user %s basket adding title %s", id.toString(), titleId.toString()));
        jdbcTemplate.update("""
                insert into users_titles(user_id, title_id)
                values (?, ?);
                """, id, titleId);
        log.info("Success");
        return ResponseEntity.ok("Added successfully");
    }

    public ResponseEntity<?> deleteTitleFromFavorites(Long id, TitleDTO titleDTO) {
        Long titleId = titleMapper.toEntity(titleDTO).getId();
        log.info(String.format("Trying to delete title %s from basket user %s", titleId.toString(), id.toString()));
        jdbcTemplate.update("""
                delete from users_titles
                where user_id=? and title_id=?;
                """, id, titleId);
        log.info("Success");
        return ResponseEntity.ok("Deleted successfully");
    }
}
