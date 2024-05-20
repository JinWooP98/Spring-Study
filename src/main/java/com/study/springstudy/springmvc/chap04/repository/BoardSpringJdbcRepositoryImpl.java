package com.study.springstudy.springmvc.chap04.repository;

import com.study.springstudy.springmvc.chap04.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
// 구현체는 관례적으로 끝에 Impl 을 붙인다.
public class BoardSpringJdbcRepositoryImpl implements BoardRepository{

    private final JdbcTemplate template;

    @Override
    public List<Board> findAll() {
        String sql = "SELECT * FROM tbl_board";
        return template.query(sql, (rs, n) -> new Board(rs));
    }

    @Override
    public Board findOne(int boardNo) {
        String sql = "SELECT * FROM tbl_board WHERE board_no = ?";
        return template.queryForObject(sql, (rs, n) -> new Board(rs), boardNo);
    }

    public void updateViewCount(int bno) {
        String sql = "UPDATE tbl_board SET view_count = view_count + 1 WHERE board_no = ?";
        template.update(sql, bno);
    }

    @Override
    public boolean save(Board board) {
        String sql = "INSERT INTO tbl_board (title, content, writer) " +
                "value ( ? , ? , ?)";

        int result = template.update(sql, board.getTitle(), board.getContent(), board.getWriter());
        return result == 1;
    }

    @Override
    public boolean delete(int boardNo) {
        String sql = "DELETE FROM tbl_board WHERE board_no = ?";
        int result = template.update(sql, boardNo);
        return result == 1;
    }
}
