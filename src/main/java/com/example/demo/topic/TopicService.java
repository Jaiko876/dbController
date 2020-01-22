package com.example.demo.topic;

import org.jooq.DSLContext;
import org.jooq.demo.db.tables.Topicsdb;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {

    private final DSLContext dslContext;

    private List<Topic> topics = new ArrayList<>(Arrays.asList(
            new Topic(1,"Vasya"),
            new Topic(2,"Petya"),
            new Topic(3,"Anton")));

    public TopicService(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public Topic getTopic(int id) {
        return topics.stream().filter(topic -> topic.getId() == id).findFirst().get();
    }


    public void postTopic(Topic topic) {
        dslContext.insertInto(Topicsdb.TOPICSDB_, Topicsdb.TOPICSDB_.NAME)
                .values(topic.getName()).execute();
        topics.add(topic);
    }

    public void updateTopic(int id, Topic topic) {
        for (int i = 0; i < topics.size() ; i++) {
            Topic t = topics.get(i);
            if (t.getId() == id) {
                topics.set(i,topic);
                return;
            }
        }
    }

    public void deleteTopic(int id) {
        topics.removeIf(topic -> topic.getId() == id);
    }
}
