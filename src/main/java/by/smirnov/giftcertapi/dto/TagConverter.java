package by.smirnov.giftcertapi.dto;

import by.smirnov.giftcertapi.domain.Tag;
import org.mapstruct.Mapper;

@Mapper
public interface TagConverter {

    Tag convert(TagRequest request);
    TagResponse convert(Tag object);
}
