package com.rit.sucy.sql.direct;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Represents and individual entry in a MySQL table</p>
 */
public class SQLEntry
{

    private static final DateFormat FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    private SQLDatabase database;
    private SQLTable    table;
    private String      name;

    /**
     * <p>Initializes a new SQL entry</p>
     *
     * @param database database containing the entry
     * @param table    table containing the entry
     * @param name     name of the entry
     */
    public SQLEntry(SQLDatabase database, SQLTable table, String name)
    {
        this.database = database;
        this.table = table;
        this.name = name;
    }

    /**
     * <p>Retrieves the name of the entry.</p>
     *
     * @return entry name
     */
    public String getName()
    {
        return name;
    }

    /**
     * <p>Loads a set of data for an entry.</p>
     * <p>This will instead return null if there was an
     * error along the way.</p>
     *
     * @param container container for the entry data
     *
     * @return loaded container for the entry data
     */
    public <T extends ISQLEntryData> T getData(T container)
    {
        try
        {
            ResultSet set = table.query(name);
            if (set.next())
            {
                container.loadData(set);
            }
            set.close();
            return container;
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    /**
     * <p>Gets a string from the entry data.</p>
     * <p>this returns null if an error occurred or
     * there is no data set for the entry's value</p>
     *
     * @param key value key
     *
     * @return string value
     */
    public String getString(String key)
    {
        try
        {
            ResultSet set = table.query(name);
            set.next();
            String value = set.getString(key);
            set.close();
            return value;
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    /**
     * <p>Gets a int from the entry data.</p>
     * <p>this returns -1 if an error occurred or
     * there is no data set for the entry's value</p>
     *
     * @param key value key
     *
     * @return int value
     */
    public int getInt(String key)
    {
        try
        {
            ResultSet set = table.query(name);
            set.next();
            int value = set.getInt(key);
            set.close();
            return value;
        }
        catch (Exception ex)
        {
            return -1;
        }
    }

    /**
     * <p>Gets a float from the entry data.</p>
     * <p>this returns -1 if an error occurred or
     * there is no data set for the entry's value</p>
     *
     * @param key value key
     *
     * @return float value
     */
    public float getFloat(String key)
    {
        try
        {
            ResultSet set = table.query(name);
            set.next();
            float value = set.getFloat(key);
            set.close();
            return value;
        }
        catch (Exception ex)
        {
            return -1;
        }
    }

    /**
     * <p>Gets a double from the entry data.</p>
     * <p>this returns -1 if an error occurred or
     * there is no data set for the entry's value</p>
     *
     * @param key value key
     *
     * @return double value
     */
    public double getDouble(String key)
    {
        try
        {
            ResultSet set = table.query(name);
            set.next();
            double value = set.getDouble(key);
            set.close();
            return value;
        }
        catch (Exception ex)
        {
            return -1;
        }
    }

    /**
     * <p>Gets a date from the entry data.</p>
     * <p>this returns null if an error occurred or
     * there is no data set for the entry's value</p>
     *
     * @param key value key
     *
     * @return date value
     */
    public Date getDate(String key)
    {
        try
        {
            ResultSet set = table.query(name);
            set.next();
            Date value = set.getDate(key);
            set.close();
            return value;
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    /**
     * <p>Sets a value for the entry.</p>
     *
     * @param key   key for the value
     * @param value value to set
     */
    public void set(String key, String value)
    {
        try
        {
            database.getStatement().execute("UPDATE " + table.getName() + " SET " + key + "='" + value + "' WHERE Name='" + name + "'");
        }
        catch (Exception ex)
        {
            database.getLogger().severe("Failed to set a value for \"" + name + "\" - " + ex.getMessage());
        }
    }

    /**
     * <p>Sets a value for the entry.</p>
     *
     * @param key   key for the value
     * @param value value to set
     */
    public void set(String key, int value)
    {
        try
        {
            database.getStatement().execute("UPDATE " + table.getName() + " SET " + key + "=" + value + " WHERE Name='" + name + "'");
        }
        catch (Exception ex)
        {
            database.getLogger().severe("Failed to set a value for \"" + name + "\" - " + ex.getMessage());
        }
    }

    /**
     * <p>Sets a value for the entry.</p>
     *
     * @param key   key for the value
     * @param value value to set
     */
    public void set(String key, double value)
    {
        try
        {
            database.getStatement().execute("UPDATE " + table.getName() + " SET " + key + "=" + value + " WHERE Name='" + name + "'");
        }
        catch (Exception ex)
        {
            database.getLogger().severe("Failed to set a value for \"" + name + "\" - " + ex.getMessage());
        }
    }

    /**
     * <p>Sets a value for the entry.</p>
     *
     * @param key   key for the value
     * @param value value to set
     */
    public void set(String key, float value)
    {
        try
        {
            database.getStatement().execute("UPDATE " + table.getName() + " SET " + key + "=" + value + " WHERE Name='" + name + "'");
        }
        catch (Exception ex)
        {
            database.getLogger().severe("Failed to set a value for \"" + name + "\" - " + ex.getMessage());
        }
    }

    /**
     * <p>Sets a value for the entry.</p>
     *
     * @param key   key for the value
     * @param value value to set
     */
    public void set(String key, Date value)
    {
        try
        {
            database.getStatement().execute("UPDATE " + table.getName() + " SET " + key + "='" + FORMAT.format(value) + "' WHERE Name='" + name + "'");
        }
        catch (Exception ex)
        {
            database.getLogger().severe("Failed to set a value for \"" + name + "\" - " + ex.getMessage());
        }
    }

    /**
     * <p>Sets a collection of data to the entry.</p>
     *
     * @param data data to set in the format (ColumnName, Value)
     */
    public void set(HashMap<String, Object> data)
    {
        StringBuilder sb = new StringBuilder("UPDATE " + table.getName() + " SET ");
        for (Map.Entry<String, Object> entry : data.entrySet())
        {
            sb.append(entry.getKey());
            sb.append('=');
            boolean num = entry.getValue() instanceof Number;
            if (!num) sb.append('\'');
            if (entry.getValue() instanceof Date) sb.append(FORMAT.format((Date) entry.getValue()));
            else sb.append(entry.getValue());
            if (!num) sb.append('\'');
            sb.append(" WHERE Name='");
            sb.append(name);
            sb.append('\'');
        }

        try
        {
            database.getStatement().execute(sb.toString());
        }
        catch (Exception ex)
        {
            database.getLogger().severe("Failed to set a value for \"" + name + "\" - " + ex.getMessage());
        }
    }
}
