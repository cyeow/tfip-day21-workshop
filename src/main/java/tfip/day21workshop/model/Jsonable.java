package tfip.day21workshop.model;

import jakarta.json.JsonObject;

public interface Jsonable {
    
    public JsonObject toJSON();
}
