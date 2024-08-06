package com.turkcell.notificationservice.repository;

import com.turkcell.notificationservice.entity.Notification;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CouchbaseRepository<Notification, Long> {
}
