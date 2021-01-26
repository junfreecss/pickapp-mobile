package com.pickapp.data.repository.sources;

public interface IPreferencesSource {

    void setString(String key, String value);

    String getString(String key);

    void setObject(String key, Object object);

}
