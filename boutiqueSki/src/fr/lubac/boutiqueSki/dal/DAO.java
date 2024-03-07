package fr.lubac.boutiqueSki.dal;

import java.util.List;

public interface DAO<T> {
    public T selectById(int id) throws DALException;

    public List<T> selectAll() throws DALException;

    public void update(T article) throws DALException;

    public void insert(T article) throws DALException;

    public void delete(int id) throws DALException;
}
