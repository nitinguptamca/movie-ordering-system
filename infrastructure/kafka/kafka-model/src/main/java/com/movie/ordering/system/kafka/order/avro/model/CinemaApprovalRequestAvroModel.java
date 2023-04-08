/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.movie.ordering.system.kafka.order.avro.model;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

@org.apache.avro.specific.AvroGenerated
public class CinemaApprovalRequestAvroModel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7111439513859653426L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"CinemaApprovalRequestAvroModel\",\"namespace\":\"com.movie.ordering.system.kafka.order.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"sagaId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"cinemaId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"orderId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"cinemaOrderStatus\",\"type\":{\"type\":\"enum\",\"name\":\"CinemaOrderStatus\",\"symbols\":[\"PAID\"]}},{\"name\":\"movies\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Movie\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"logicalType\":\"uuid\"},{\"name\":\"quantity\",\"type\":\"int\"}]}}},{\"name\":\"price\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":2}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
  }

  private static final BinaryMessageEncoder<CinemaApprovalRequestAvroModel> ENCODER =
      new BinaryMessageEncoder<CinemaApprovalRequestAvroModel>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<CinemaApprovalRequestAvroModel> DECODER =
      new BinaryMessageDecoder<CinemaApprovalRequestAvroModel>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<CinemaApprovalRequestAvroModel> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<CinemaApprovalRequestAvroModel> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<CinemaApprovalRequestAvroModel> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<CinemaApprovalRequestAvroModel>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this CinemaApprovalRequestAvroModel to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a CinemaApprovalRequestAvroModel from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a CinemaApprovalRequestAvroModel instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static CinemaApprovalRequestAvroModel fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String id;
  private java.lang.String sagaId;
  private java.lang.String cinemaId;
  private java.lang.String orderId;
  private com.movie.ordering.system.kafka.order.avro.model.CinemaOrderStatus cinemaOrderStatus;
  private java.util.List<com.movie.ordering.system.kafka.order.avro.model.Movie> movies;
  private java.math.BigDecimal price;
  private java.time.Instant createdAt;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public CinemaApprovalRequestAvroModel() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param sagaId The new value for sagaId
   * @param cinemaId The new value for cinemaId
   * @param orderId The new value for orderId
   * @param cinemaOrderStatus The new value for cinemaOrderStatus
   * @param movies The new value for movies
   * @param price The new value for price
   * @param createdAt The new value for createdAt
   */
  public CinemaApprovalRequestAvroModel(java.lang.String id, java.lang.String sagaId, java.lang.String cinemaId, java.lang.String orderId, com.movie.ordering.system.kafka.order.avro.model.CinemaOrderStatus cinemaOrderStatus, java.util.List<com.movie.ordering.system.kafka.order.avro.model.Movie> movies, java.math.BigDecimal price, java.time.Instant createdAt) {
    this.id = id;
    this.sagaId = sagaId;
    this.cinemaId = cinemaId;
    this.orderId = orderId;
    this.cinemaOrderStatus = cinemaOrderStatus;
    this.movies = movies;
    this.price = price;
    this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return sagaId;
    case 2: return cinemaId;
    case 3: return orderId;
    case 4: return cinemaOrderStatus;
    case 5: return movies;
    case 6: return price;
    case 7: return createdAt;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      null,
      null,
      null,
      null,
      null,
      null,
      new org.apache.avro.Conversions.DecimalConversion(),
      new org.apache.avro.data.TimeConversions.TimestampMillisConversion(),
      null
  };

  @Override
  public org.apache.avro.Conversion<?> getConversion(int field) {
    return conversions[field];
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = value$ != null ? value$.toString() : null; break;
    case 1: sagaId = value$ != null ? value$.toString() : null; break;
    case 2: cinemaId = value$ != null ? value$.toString() : null; break;
    case 3: orderId = value$ != null ? value$.toString() : null; break;
    case 4: cinemaOrderStatus = (com.movie.ordering.system.kafka.order.avro.model.CinemaOrderStatus)value$; break;
    case 5: movies = (java.util.List<com.movie.ordering.system.kafka.order.avro.model.Movie>)value$; break;
    case 6: price = (java.math.BigDecimal)value$; break;
    case 7: createdAt = (java.time.Instant)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.String getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.String value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'sagaId' field.
   * @return The value of the 'sagaId' field.
   */
  public java.lang.String getSagaId() {
    return sagaId;
  }


  /**
   * Sets the value of the 'sagaId' field.
   * @param value the value to set.
   */
  public void setSagaId(java.lang.String value) {
    this.sagaId = value;
  }

  /**
   * Gets the value of the 'cinemaId' field.
   * @return The value of the 'cinemaId' field.
   */
  public java.lang.String getCinemaId() {
    return cinemaId;
  }


  /**
   * Sets the value of the 'cinemaId' field.
   * @param value the value to set.
   */
  public void setCinemaId(java.lang.String value) {
    this.cinemaId = value;
  }

  /**
   * Gets the value of the 'orderId' field.
   * @return The value of the 'orderId' field.
   */
  public java.lang.String getOrderId() {
    return orderId;
  }


  /**
   * Sets the value of the 'orderId' field.
   * @param value the value to set.
   */
  public void setOrderId(java.lang.String value) {
    this.orderId = value;
  }

  /**
   * Gets the value of the 'cinemaOrderStatus' field.
   * @return The value of the 'cinemaOrderStatus' field.
   */
  public com.movie.ordering.system.kafka.order.avro.model.CinemaOrderStatus getCinemaOrderStatus() {
    return cinemaOrderStatus;
  }


  /**
   * Sets the value of the 'cinemaOrderStatus' field.
   * @param value the value to set.
   */
  public void setCinemaOrderStatus(com.movie.ordering.system.kafka.order.avro.model.CinemaOrderStatus value) {
    this.cinemaOrderStatus = value;
  }

  /**
   * Gets the value of the 'movies' field.
   * @return The value of the 'movies' field.
   */
  public java.util.List<com.movie.ordering.system.kafka.order.avro.model.Movie> getMovies() {
    return movies;
  }


  /**
   * Sets the value of the 'movies' field.
   * @param value the value to set.
   */
  public void setMovies(java.util.List<com.movie.ordering.system.kafka.order.avro.model.Movie> value) {
    this.movies = value;
  }

  /**
   * Gets the value of the 'price' field.
   * @return The value of the 'price' field.
   */
  public java.math.BigDecimal getPrice() {
    return price;
  }


  /**
   * Sets the value of the 'price' field.
   * @param value the value to set.
   */
  public void setPrice(java.math.BigDecimal value) {
    this.price = value;
  }

  /**
   * Gets the value of the 'createdAt' field.
   * @return The value of the 'createdAt' field.
   */
  public java.time.Instant getCreatedAt() {
    return createdAt;
  }


  /**
   * Sets the value of the 'createdAt' field.
   * @param value the value to set.
   */
  public void setCreatedAt(java.time.Instant value) {
    this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
  }

  /**
   * Creates a new CinemaApprovalRequestAvroModel RecordBuilder.
   * @return A new CinemaApprovalRequestAvroModel RecordBuilder
   */
  public static com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder newBuilder() {
    return new com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder();
  }

  /**
   * Creates a new CinemaApprovalRequestAvroModel RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new CinemaApprovalRequestAvroModel RecordBuilder
   */
  public static com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder newBuilder(com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder other) {
    if (other == null) {
      return new com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder();
    } else {
      return new com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder(other);
    }
  }

  /**
   * Creates a new CinemaApprovalRequestAvroModel RecordBuilder by copying an existing CinemaApprovalRequestAvroModel instance.
   * @param other The existing instance to copy.
   * @return A new CinemaApprovalRequestAvroModel RecordBuilder
   */
  public static com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder newBuilder(com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel other) {
    if (other == null) {
      return new com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder();
    } else {
      return new com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder(other);
    }
  }

  /**
   * RecordBuilder for CinemaApprovalRequestAvroModel instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<CinemaApprovalRequestAvroModel>
    implements org.apache.avro.data.RecordBuilder<CinemaApprovalRequestAvroModel> {

    private java.lang.String id;
    private java.lang.String sagaId;
    private java.lang.String cinemaId;
    private java.lang.String orderId;
    private com.movie.ordering.system.kafka.order.avro.model.CinemaOrderStatus cinemaOrderStatus;
    private java.util.List<com.movie.ordering.system.kafka.order.avro.model.Movie> movies;
    private java.math.BigDecimal price;
    private java.time.Instant createdAt;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.sagaId)) {
        this.sagaId = data().deepCopy(fields()[1].schema(), other.sagaId);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.cinemaId)) {
        this.cinemaId = data().deepCopy(fields()[2].schema(), other.cinemaId);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.orderId)) {
        this.orderId = data().deepCopy(fields()[3].schema(), other.orderId);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.cinemaOrderStatus)) {
        this.cinemaOrderStatus = data().deepCopy(fields()[4].schema(), other.cinemaOrderStatus);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.movies)) {
        this.movies = data().deepCopy(fields()[5].schema(), other.movies);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.price)) {
        this.price = data().deepCopy(fields()[6].schema(), other.price);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[7].schema(), other.createdAt);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
    }

    /**
     * Creates a Builder by copying an existing CinemaApprovalRequestAvroModel instance
     * @param other The existing instance to copy.
     */
    private Builder(com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.sagaId)) {
        this.sagaId = data().deepCopy(fields()[1].schema(), other.sagaId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.cinemaId)) {
        this.cinemaId = data().deepCopy(fields()[2].schema(), other.cinemaId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.orderId)) {
        this.orderId = data().deepCopy(fields()[3].schema(), other.orderId);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.cinemaOrderStatus)) {
        this.cinemaOrderStatus = data().deepCopy(fields()[4].schema(), other.cinemaOrderStatus);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.movies)) {
        this.movies = data().deepCopy(fields()[5].schema(), other.movies);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.price)) {
        this.price = data().deepCopy(fields()[6].schema(), other.price);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[7].schema(), other.createdAt);
        fieldSetFlags()[7] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.String getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder setId(java.lang.String value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'sagaId' field.
      * @return The value.
      */
    public java.lang.String getSagaId() {
      return sagaId;
    }


    /**
      * Sets the value of the 'sagaId' field.
      * @param value The value of 'sagaId'.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder setSagaId(java.lang.String value) {
      validate(fields()[1], value);
      this.sagaId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'sagaId' field has been set.
      * @return True if the 'sagaId' field has been set, false otherwise.
      */
    public boolean hasSagaId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'sagaId' field.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder clearSagaId() {
      sagaId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'cinemaId' field.
      * @return The value.
      */
    public java.lang.String getCinemaId() {
      return cinemaId;
    }


    /**
      * Sets the value of the 'cinemaId' field.
      * @param value The value of 'cinemaId'.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder setCinemaId(java.lang.String value) {
      validate(fields()[2], value);
      this.cinemaId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'cinemaId' field has been set.
      * @return True if the 'cinemaId' field has been set, false otherwise.
      */
    public boolean hasCinemaId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'cinemaId' field.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder clearCinemaId() {
      cinemaId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'orderId' field.
      * @return The value.
      */
    public java.lang.String getOrderId() {
      return orderId;
    }


    /**
      * Sets the value of the 'orderId' field.
      * @param value The value of 'orderId'.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder setOrderId(java.lang.String value) {
      validate(fields()[3], value);
      this.orderId = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'orderId' field has been set.
      * @return True if the 'orderId' field has been set, false otherwise.
      */
    public boolean hasOrderId() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'orderId' field.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder clearOrderId() {
      orderId = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'cinemaOrderStatus' field.
      * @return The value.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaOrderStatus getCinemaOrderStatus() {
      return cinemaOrderStatus;
    }


    /**
      * Sets the value of the 'cinemaOrderStatus' field.
      * @param value The value of 'cinemaOrderStatus'.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder setCinemaOrderStatus(com.movie.ordering.system.kafka.order.avro.model.CinemaOrderStatus value) {
      validate(fields()[4], value);
      this.cinemaOrderStatus = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'cinemaOrderStatus' field has been set.
      * @return True if the 'cinemaOrderStatus' field has been set, false otherwise.
      */
    public boolean hasCinemaOrderStatus() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'cinemaOrderStatus' field.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder clearCinemaOrderStatus() {
      cinemaOrderStatus = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'movies' field.
      * @return The value.
      */
    public java.util.List<com.movie.ordering.system.kafka.order.avro.model.Movie> getMovies() {
      return movies;
    }


    /**
      * Sets the value of the 'movies' field.
      * @param value The value of 'movies'.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder setMovies(java.util.List<com.movie.ordering.system.kafka.order.avro.model.Movie> value) {
      validate(fields()[5], value);
      this.movies = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'movies' field has been set.
      * @return True if the 'movies' field has been set, false otherwise.
      */
    public boolean hasMovies() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'movies' field.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder clearMovies() {
      movies = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'price' field.
      * @return The value.
      */
    public java.math.BigDecimal getPrice() {
      return price;
    }


    /**
      * Sets the value of the 'price' field.
      * @param value The value of 'price'.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder setPrice(java.math.BigDecimal value) {
      validate(fields()[6], value);
      this.price = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'price' field has been set.
      * @return True if the 'price' field has been set, false otherwise.
      */
    public boolean hasPrice() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'price' field.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder clearPrice() {
      price = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'createdAt' field.
      * @return The value.
      */
    public java.time.Instant getCreatedAt() {
      return createdAt;
    }


    /**
      * Sets the value of the 'createdAt' field.
      * @param value The value of 'createdAt'.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder setCreatedAt(java.time.Instant value) {
      validate(fields()[7], value);
      this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'createdAt' field has been set.
      * @return True if the 'createdAt' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'createdAt' field.
      * @return This builder.
      */
    public com.movie.ordering.system.kafka.order.avro.model.CinemaApprovalRequestAvroModel.Builder clearCreatedAt() {
      fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CinemaApprovalRequestAvroModel build() {
      try {
        CinemaApprovalRequestAvroModel record = new CinemaApprovalRequestAvroModel();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.String) defaultValue(fields()[0]);
        record.sagaId = fieldSetFlags()[1] ? this.sagaId : (java.lang.String) defaultValue(fields()[1]);
        record.cinemaId = fieldSetFlags()[2] ? this.cinemaId : (java.lang.String) defaultValue(fields()[2]);
        record.orderId = fieldSetFlags()[3] ? this.orderId : (java.lang.String) defaultValue(fields()[3]);
        record.cinemaOrderStatus = fieldSetFlags()[4] ? this.cinemaOrderStatus : (com.movie.ordering.system.kafka.order.avro.model.CinemaOrderStatus) defaultValue(fields()[4]);
        record.movies = fieldSetFlags()[5] ? this.movies : (java.util.List<com.movie.ordering.system.kafka.order.avro.model.Movie>) defaultValue(fields()[5]);
        record.price = fieldSetFlags()[6] ? this.price : (java.math.BigDecimal) defaultValue(fields()[6]);
        record.createdAt = fieldSetFlags()[7] ? this.createdAt : (java.time.Instant) defaultValue(fields()[7]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<CinemaApprovalRequestAvroModel>
    WRITER$ = (org.apache.avro.io.DatumWriter<CinemaApprovalRequestAvroModel>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<CinemaApprovalRequestAvroModel>
    READER$ = (org.apache.avro.io.DatumReader<CinemaApprovalRequestAvroModel>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










