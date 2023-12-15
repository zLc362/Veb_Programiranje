package mk.finki.ukim.mk.lab.convertors;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mk.finki.ukim.mk.lab.model.AuthorFullname;
@Converter
public class AuthorFullnameConverter implements AttributeConverter<AuthorFullname,String> {
    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(AuthorFullname fullname) {
        if (fullname == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (fullname.getSurname() != null && !fullname.getSurname()
                .isEmpty()) {
            sb.append(fullname.getSurname());
            sb.append(SEPARATOR);
        }

        if (fullname.getName() != null
                && !fullname.getName().isEmpty()) {
            sb.append(fullname.getName());
        }

        return sb.toString();
    }

    @Override
    public AuthorFullname convertToEntityAttribute(String dbUserFullname) {
        if (dbUserFullname == null || dbUserFullname.isEmpty()) {
            return null;
        }

        String[] pieces = dbUserFullname.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        AuthorFullname fullname = new AuthorFullname();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbUserFullname.contains(SEPARATOR)) {
            fullname.setSurname(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                fullname.setName(pieces[1]);
            }
        } else {
            fullname.setName(firstPiece);
        }

        return fullname;
    }

}
