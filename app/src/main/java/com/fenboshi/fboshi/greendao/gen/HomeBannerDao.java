package com.fenboshi.fboshi.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.fenboshi.fboshi.bean.HomeBanner;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HOME_BANNER".
*/
public class HomeBannerDao extends AbstractDao<HomeBanner, Void> {

    public static final String TABLENAME = "HOME_BANNER";

    /**
     * Properties of entity HomeBanner.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Desc = new Property(0, String.class, "desc", false, "DESC");
        public final static Property Id = new Property(1, String.class, "id", false, "ID");
        public final static Property ImagePath = new Property(2, String.class, "imagePath", false, "IMAGE_PATH");
        public final static Property IsVisible = new Property(3, int.class, "isVisible", false, "IS_VISIBLE");
        public final static Property Type = new Property(4, int.class, "type", false, "TYPE");
        public final static Property Title = new Property(5, String.class, "title", false, "TITLE");
        public final static Property Url = new Property(6, String.class, "url", false, "URL");
    }


    public HomeBannerDao(DaoConfig config) {
        super(config);
    }
    
    public HomeBannerDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HOME_BANNER\" (" + //
                "\"DESC\" TEXT," + // 0: desc
                "\"ID\" TEXT," + // 1: id
                "\"IMAGE_PATH\" TEXT," + // 2: imagePath
                "\"IS_VISIBLE\" INTEGER NOT NULL ," + // 3: isVisible
                "\"TYPE\" INTEGER NOT NULL ," + // 4: type
                "\"TITLE\" TEXT," + // 5: title
                "\"URL\" TEXT);"); // 6: url
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HOME_BANNER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HomeBanner entity) {
        stmt.clearBindings();
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(1, desc);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String imagePath = entity.getImagePath();
        if (imagePath != null) {
            stmt.bindString(3, imagePath);
        }
        stmt.bindLong(4, entity.getIsVisible());
        stmt.bindLong(5, entity.getType());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(6, title);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(7, url);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HomeBanner entity) {
        stmt.clearBindings();
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(1, desc);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String imagePath = entity.getImagePath();
        if (imagePath != null) {
            stmt.bindString(3, imagePath);
        }
        stmt.bindLong(4, entity.getIsVisible());
        stmt.bindLong(5, entity.getType());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(6, title);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(7, url);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public HomeBanner readEntity(Cursor cursor, int offset) {
        HomeBanner entity = new HomeBanner( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // desc
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // imagePath
            cursor.getInt(offset + 3), // isVisible
            cursor.getInt(offset + 4), // type
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // title
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // url
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HomeBanner entity, int offset) {
        entity.setDesc(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setImagePath(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIsVisible(cursor.getInt(offset + 3));
        entity.setType(cursor.getInt(offset + 4));
        entity.setTitle(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUrl(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(HomeBanner entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(HomeBanner entity) {
        return null;
    }

    @Override
    public boolean hasKey(HomeBanner entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
