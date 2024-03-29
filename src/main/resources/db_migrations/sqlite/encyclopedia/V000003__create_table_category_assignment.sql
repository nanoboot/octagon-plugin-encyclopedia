CREATE TABLE "CATEGORY_ASSIGNMENT" (
 "ID" TEXT,
 "ARTICLE_ID" TEXT,
 "CATEGORY_ID" TEXT,
 "PARENT_CATEGORY_ID" TEXT NOT NULL,
 FOREIGN KEY("CATEGORY_ID") REFERENCES "CATEGORY"("ID"),
 FOREIGN KEY("ARTICLE_ID") REFERENCES "ARTICLE"("ID"),
 FOREIGN KEY("PARENT_CATEGORY_ID") REFERENCES "CATEGORY"("ID"),
 PRIMARY KEY("ID")
);
