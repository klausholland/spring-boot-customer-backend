package com.hollandklaus.customer_backend.web.validation;

public class ValidationConstants {

    // general validation messages
    public static final String NOT_NULL_MESSAGE = "must not be null";
    public static final String MIN_MAX_SIZE_MESSAGE = "must have a length between {min} and {max} characters";
    public static final String MAX_SIZE_MESSAGE = "must have less than {max} characters";
    public static final String PATTERN_MESSAGE = "must be of pattern {regexp}";

    // unknown country messages
    public static final String UNKNOWN_COUNTRY_TAX_ID_MESSAGE = "Could not validate tax id for unsupported country";
    public static final String UNKNOWN_COUNTRY_POSTAL_CODE_MESSAGE = "Could not validate postal code for unsupported country";

    // postal code validation messages
    public static final String DE_POSTAL_CODE_VALIDATION_MESSAGE = "German postal code must have 5 digits";
    public static final String AT_POSTAL_CODE_VALIDATION_MESSAGE = "Austrian postal code must have 4 digits";
    public static final String FR_POSTAL_CODE_VALIDATION_MESSAGE = "France postal code must have 5 digits";
    public static final String GB_POSTAL_CODE_VALIDATION_MESSAGE = "Great Britain postal code must follow the pattern "+
            "A0 0AA, A00 0AA, A0A 0AA, AA0 0AA, AA00 0AA or AA0A 0AA";
    public static final String DK_POSTAL_CODE_VALIDATION_MESSAGE = "Denmark postal code must have 4 digits";
    public static final String NL_POSTAL_CODE_VALIDATION_MESSAGE = "Netherlands postal code must have 4 digits and 2 uppercase letters";

    // tax id validation messages
    public static final String DE_TAX_ID_VALIDATION_MESSAGE = "German tax id must follow the pattern: BE099999999p or BE999999999p";
    public static final String AT_TAX_ID_VALIDATION_MESSAGE = "Austrian tax id must follow the pattern: ATU9999999p";
    public static final String FR_TAX_ID_VALIDATION_MESSAGE = "France tax id must follow the pattern: FRLL999999999, " +
            "FRL9999999999 or FR99999999999";
    public static final String GB_TAX_ID_VALIDATION_MESSAGE = "Great Britain tax id must follow the pattern: GB9999999pp999, " +
            "GBGD999 or GBHA999";
    public static final String DK_TAX_ID_VALIDATION_MESSAGE = "Denmark tax id must follow the pattern: DK99999999";
    public static final String NL_TAX_ID_VALIDATION_MESSAGE = "Netherlands tax id must follow the pattern: NL99999999pB01 " +
            "or NL999999999Bpp";
}
