package ru.mahalov.CodeReviewRESTApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.mahalov.CodeReviewRESTApp.entity.LinkDetail;
import ru.mahalov.CodeReviewRESTApp.entity.Link;
import ru.mahalov.CodeReviewRESTApp.entity.RequestData;

import java.util.List;

@Repository
public interface DetailLinkRepository extends JpaRepository<LinkDetail,Long> {
    @Query(value = "" +
            "SELECT " +
            "  0 id" +
            "  , * \n" +
            "FROM \n" +
            "    (SELECT \n" +
            "       lk.Original, \n" +
            "       concat('/l/',lk.Short_Link) link, \n" +
            "       Row_Number() over (order by count(*) desc) rank, \n" +
            "       count(rd.id) as count \n" +
            "    FROM LINK lk \n" +
            "                 LEFT JOIN Request_Data rd ON lk.id = rd.link_id \n" +
            "    GROUP BY lk.Original, lk.Short_Link) \n" +
            "WHERE \n" +
            "    link = ?1 \n" +
            "ORDER BY rank \n",nativeQuery = true)
    LinkDetail getLinkDetailsByShortName(String shortLink);

    @Query(value = "" +
            "SELECT " +
            "* \n" +
            "FROM \n" +
            "    (SELECT \n " +
            "       Row_Number() over (order by lk.Original) id, " +
            "       lk.Original original, \n " +
            "       concat('/l/',lk.Short_Link) link, \n " +
            "       Row_Number() over (order by count(rd.id) desc) rank, \n " +
            "       count(rd.id) as count \n " +
            "    FROM LINK lk \n " +
            "                 LEFT JOIN Request_Data rd ON lk.id = rd.link_id \n " +
            "    GROUP BY lk.Original, lk.Short_Link) \n" +
            "ORDER BY rank \n" +
            "LIMIT ?2 \n" +
            "OFFSET ?1 ",nativeQuery = true)
    List<LinkDetail> getLinkAllLinkDetails(int countOffsetRows, int countInPage);

}


