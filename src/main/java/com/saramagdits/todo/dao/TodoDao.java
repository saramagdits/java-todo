package com.saramagdits.todo.dao;

import com.saramagdits.todo.entities.Todo;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TodoDao {

    private SessionFactory sessionFactory;

    @Autowired
    public TodoDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Todo getById(int id) {
        return (Todo) sessionFactory
                .getCurrentSession()
                .createCriteria(Todo.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    public Todo createTodo(String body) {
        Todo todo = new Todo(body);
        return (Todo) sessionFactory
                .getCurrentSession()
                .save(todo);
    }

    public Todo toggleTodo(int id) {
        Todo todo = getById(id);
        todo.setStatus(!todo.getStatus());
        sessionFactory
                .getCurrentSession()
                .update(todo);
        return todo;
    }
}
