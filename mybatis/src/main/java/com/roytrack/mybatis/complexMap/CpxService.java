package com.roytrack.mybatis.complexMap;

/**
 * a service to resolve complex resultmap in mybatis
 *
 * <resultMap id="detailedBlogResultMap" type="Blog">
 <constructor>
 <idArg column="blog_id" javaType="int"/>
 </constructor>
 <result property="title" column="blog_title"/>
 <association property="author" javaType="Author">
 <id property="id" column="author_id"/>
 <result property="username" column="author_username"/>
 <result property="password" column="author_password"/>
 <result property="email" column="author_email"/>
 <result property="bio" column="author_bio"/>
 <result property="favouriteSection" column="author_favourite_section"/>
 </association>
 <collection property="posts" ofType="Post">
 <id property="id" column="post_id"/>
 <result property="subject" column="post_subject"/>
 <association property="author" javaType="Author"/>
 <collection property="comments" ofType="Comment">
 <id property="id" column="comment_id"/>
 </collection>
 <collection property="tags" ofType="Tag" >
 <id property="id" column="tag_id"/>
 </collection>
 <discriminator javaType="int" column="draft">
 <case value="1" resultType="DraftPost"/>
 </discriminator>
 </collection>
 </resultMap>
 *
 * Created by roytrack on 2016-09-17.
 */
public class CpxService {
}
