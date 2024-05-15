package org.turkcell.common.events;

import java.time.LocalDateTime;

public class OrderCreatedEvent {
    public int id;
    public LocalDateTime localDateTime;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }



    public OrderCreatedEvent(int id, LocalDateTime localDateTime) {
        this.id = id;
        this.localDateTime = localDateTime;
    }

    public OrderCreatedEvent(){}


}
