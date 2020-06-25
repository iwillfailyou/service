package com.iwillfailyou.migrations;

import com.nikialeksey.jood.Db;
import com.nikialeksey.jood.JdException;
import com.nikialeksey.jood.Migration;
import com.nikialeksey.jood.sql.JdSql;

public class Migration3 implements Migration {
    @Override
    public int number() {
        return 3;
    }

    @Override
    public void execute(Db db) throws JdException {
        db.write(
            new JdSql(
                "CREATE TABLE nullfree (" +
                    "id TEXT NOT NULL PRIMARY KEY, " +
                    "repo TEXT NOT NULL, " +
                    "threshold INTEGER NOT NULL DEFAULT 0, " +
                    "badgeUrl TEXT NOT NULL DEFAULT '', " +
                    "FOREIGN KEY(repo) REFERENCES repo(path) ON UPDATE CASCADE ON DELETE CASCADE" +
                    ")"
            )
        );
        db.write(
            new JdSql(
                "INSERT INTO nullfree (id, repo, threshold, badgeUrl) " +
                    "SELECT " +
                    "    (hex(randomblob(4)) || " +
                    "        '-' || " +
                    "        hex(randomblob(2)) || " +
                    "        '-' || " +
                    "        '4' || " +
                    "        substr(hex(randomblob(2)), 2) || " +
                    "        '-' || " +
                    "        substr('AB89', 1 + (abs(random()) % 4) , 1) || " +
                    "        substr(hex(randomblob(2)), 2) || " +
                    "        '-' || " +
                    "        hex(randomblob(6))" +
                    "    ) as id, " +
                    "    path, " +
                    "    threshold, " +
                    "    badgeUrl " +
                    "FROM repo"
            )
        );

        db.read( // WTF??? https://github.com/xerial/sqlite-jdbc/issues/497
            new JdSql(
                "ALTER TABLE repo RENAME TO repo_old"
            )
        ).close();
        db.write(
            new JdSql(
                "CREATE TABLE repo (" +
                    "path TEXT NOT NULL PRIMARY KEY" +
                    ")"
            )
        );
        db.write(
            new JdSql(
                "INSERT INTO repo(path) SELECT path FROM repo_old"
            )
        );
        db.write(
            new JdSql(
                "DROP TABLE repo_old"
            )
        );

        db.write(
            new JdSql(
                "CREATE TABLE staticfree (" +
                    "id TEXT NOT NULL PRIMARY KEY, " +
                    "repo TEXT NOT NULL, " +
                    "threshold INTEGER NOT NULL DEFAULT 0, " +
                    "badgeUrl TEXT NOT NULL DEFAULT '', " +
                    "FOREIGN KEY(repo) REFERENCES repo(path) ON UPDATE CASCADE ON DELETE CASCADE" +
                    ")"
            )
        );
        db.write(
            new JdSql(
                "CREATE TABLE staticfree_violation (" +
                    "id TEXT NOT NULL PRIMARY KEY," +
                    "repo TEXT NOT NULL," +
                    "description TEXT NOT NULL," +
                    "FOREIGN KEY(repo) REFERENCES repo(path) ON UPDATE CASCADE ON DELETE CASCADE" +
                    ")"
            )
        );
    }
}
