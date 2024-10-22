package com.acing.techmaps.persistence.external;

import com.acing.techmaps.domain.entities.group.Group;
import com.acing.techmaps.usecases.group.gateway.GroupDAO;
import com.acing.techmaps.web.exception.HttpException;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class GroupDAOImpl implements GroupDAO {

    private JdbcTemplate jdbcTemplate;

    public GroupDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.group-dao.insert.group}")
    private String insertGroupQuery;

    @Value("${queries.sql.group-dao.select.group-by-id}")
    private String selectGroupByIdQuery;

    @Value("${queries.sql.group-dao.select.group-by-name}")
    private String selectGroupByNameQuery;

    @Value("${queries.sql.group-dao.select.group-by-parent-id}")
    private String selectGroupByParentIdQuery;

    @Value("${queries.sql.group-dao.update.group}")
    private String updateGroupQuery;

    @Value("${queries.sql.group-dao.exists.group-by-id}")
    private String existsGroupByIdQuery;

    @Override
    public Group add(Group group) {
        UUID id = Generators.timeBasedEpochGenerator().generate();
        jdbcTemplate.update(insertGroupQuery, id, group.getName());
        return Group.createFull(id, group.getParentId(), group.getName());
    }

    @Override
    public Group findById(UUID id) {
        try {
            return jdbcTemplate.queryForObject(selectGroupByIdQuery, this::mapperGroupFromRs, id);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group with id: " + id);
        }
    }

    @Override
    public Group findByName(String name) {
        try {
            return jdbcTemplate.queryForObject(selectGroupByNameQuery, this::mapperGroupFromRs, name);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find group with name: " + name);
        }
    }

    @Override
    public List<Group> findByParentId(UUID parentId) {
        try {
            return jdbcTemplate.query(selectGroupByParentIdQuery, this::mapperGroupFromRs, parentId);
        } catch (EmptyResultDataAccessException err) {
            throw new HttpException(HttpStatus.NOT_FOUND, "Could not find groups with parent id: " + parentId);
        }
    }

    @Override
    public Group update(Group group) {
        jdbcTemplate.update(updateGroupQuery, group.getName(), group.getParentId(), group.getId());
        return Group.createFull(group.getId(), group.getParentId(), group.getName());
    }

    @Override
    public Boolean groupExists(UUID id) {
        return jdbcTemplate.queryForObject(existsGroupByIdQuery, Boolean.class, id);
    }

    private Group mapperGroupFromRs(ResultSet rs, int rowNum) throws SQLException {
        return Group.createFull(
                (UUID) rs.getObject("id"),
                (UUID) rs.getObject("parent_id"),
                rs.getString("name")
        );
    }

}
