package com.turkcell.notificationservice.repository;

import com.turkcell.notificationservice.entity.NotificationUpdate;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface NotificationUpdatedRepository extends CouchbaseRepository<NotificationUpdate, Long> {
}
