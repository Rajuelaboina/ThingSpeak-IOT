package com.thingspeak.thingspeak_iot;

public class Feeds {
   private final String created_at ;
    private final String  entry_id ;
    private final String field1 ;

    public Feeds(String created_at, String entry_id, String field1) {
        this.created_at = created_at;
        this.entry_id = entry_id;
        this.field1 = field1;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getEntry_id() {
        return entry_id;
    }

    public String getField1() {
        return field1;
    }
}
