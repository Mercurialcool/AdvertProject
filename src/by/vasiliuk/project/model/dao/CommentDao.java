package by.vasiliuk.project.model.dao;

import by.vasiliuk.project.model.entity.Comment;

import java.util.List;

public interface CommentDao extends BaseDao{
    List<Comment> findAllByAdvertId(long id) throws DaoException;
}
