// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: sentinel/provider/v1/params.proto

package sentinel.provider.v1;

public final class ParamsOuterClass {
  private ParamsOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ParamsOrBuilder extends
      // @@protoc_insertion_point(interface_extends:sentinel.provider.v1.Params)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
     * @return Whether the deposit field is set.
     */
    boolean hasDeposit();
    /**
     * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
     * @return The deposit.
     */
    cosmos.base.v1beta1.CoinOuterClass.Coin getDeposit();
    /**
     * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
     */
    cosmos.base.v1beta1.CoinOuterClass.CoinOrBuilder getDepositOrBuilder();
  }
  /**
   * Protobuf type {@code sentinel.provider.v1.Params}
   */
  public static final class Params extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:sentinel.provider.v1.Params)
      ParamsOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Params.newBuilder() to construct.
    private Params(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Params() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Params();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Params(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              cosmos.base.v1beta1.CoinOuterClass.Coin.Builder subBuilder = null;
              if (deposit_ != null) {
                subBuilder = deposit_.toBuilder();
              }
              deposit_ = input.readMessage(cosmos.base.v1beta1.CoinOuterClass.Coin.parser(), extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(deposit_);
                deposit_ = subBuilder.buildPartial();
              }

              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sentinel.provider.v1.ParamsOuterClass.internal_static_sentinel_provider_v1_Params_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sentinel.provider.v1.ParamsOuterClass.internal_static_sentinel_provider_v1_Params_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sentinel.provider.v1.ParamsOuterClass.Params.class, sentinel.provider.v1.ParamsOuterClass.Params.Builder.class);
    }

    public static final int DEPOSIT_FIELD_NUMBER = 1;
    private cosmos.base.v1beta1.CoinOuterClass.Coin deposit_;
    /**
     * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
     * @return Whether the deposit field is set.
     */
    @java.lang.Override
    public boolean hasDeposit() {
      return deposit_ != null;
    }
    /**
     * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
     * @return The deposit.
     */
    @java.lang.Override
    public cosmos.base.v1beta1.CoinOuterClass.Coin getDeposit() {
      return deposit_ == null ? cosmos.base.v1beta1.CoinOuterClass.Coin.getDefaultInstance() : deposit_;
    }
    /**
     * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
     */
    @java.lang.Override
    public cosmos.base.v1beta1.CoinOuterClass.CoinOrBuilder getDepositOrBuilder() {
      return getDeposit();
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (deposit_ != null) {
        output.writeMessage(1, getDeposit());
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (deposit_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, getDeposit());
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof sentinel.provider.v1.ParamsOuterClass.Params)) {
        return super.equals(obj);
      }
      sentinel.provider.v1.ParamsOuterClass.Params other = (sentinel.provider.v1.ParamsOuterClass.Params) obj;

      if (hasDeposit() != other.hasDeposit()) return false;
      if (hasDeposit()) {
        if (!getDeposit()
            .equals(other.getDeposit())) return false;
      }
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasDeposit()) {
        hash = (37 * hash) + DEPOSIT_FIELD_NUMBER;
        hash = (53 * hash) + getDeposit().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static sentinel.provider.v1.ParamsOuterClass.Params parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static sentinel.provider.v1.ParamsOuterClass.Params parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static sentinel.provider.v1.ParamsOuterClass.Params parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static sentinel.provider.v1.ParamsOuterClass.Params parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static sentinel.provider.v1.ParamsOuterClass.Params parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static sentinel.provider.v1.ParamsOuterClass.Params parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static sentinel.provider.v1.ParamsOuterClass.Params parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static sentinel.provider.v1.ParamsOuterClass.Params parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static sentinel.provider.v1.ParamsOuterClass.Params parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static sentinel.provider.v1.ParamsOuterClass.Params parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static sentinel.provider.v1.ParamsOuterClass.Params parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static sentinel.provider.v1.ParamsOuterClass.Params parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(sentinel.provider.v1.ParamsOuterClass.Params prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code sentinel.provider.v1.Params}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:sentinel.provider.v1.Params)
        sentinel.provider.v1.ParamsOuterClass.ParamsOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return sentinel.provider.v1.ParamsOuterClass.internal_static_sentinel_provider_v1_Params_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return sentinel.provider.v1.ParamsOuterClass.internal_static_sentinel_provider_v1_Params_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                sentinel.provider.v1.ParamsOuterClass.Params.class, sentinel.provider.v1.ParamsOuterClass.Params.Builder.class);
      }

      // Construct using sentinel.provider.v1.ParamsOuterClass.Params.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        if (depositBuilder_ == null) {
          deposit_ = null;
        } else {
          deposit_ = null;
          depositBuilder_ = null;
        }
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return sentinel.provider.v1.ParamsOuterClass.internal_static_sentinel_provider_v1_Params_descriptor;
      }

      @java.lang.Override
      public sentinel.provider.v1.ParamsOuterClass.Params getDefaultInstanceForType() {
        return sentinel.provider.v1.ParamsOuterClass.Params.getDefaultInstance();
      }

      @java.lang.Override
      public sentinel.provider.v1.ParamsOuterClass.Params build() {
        sentinel.provider.v1.ParamsOuterClass.Params result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public sentinel.provider.v1.ParamsOuterClass.Params buildPartial() {
        sentinel.provider.v1.ParamsOuterClass.Params result = new sentinel.provider.v1.ParamsOuterClass.Params(this);
        if (depositBuilder_ == null) {
          result.deposit_ = deposit_;
        } else {
          result.deposit_ = depositBuilder_.build();
        }
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof sentinel.provider.v1.ParamsOuterClass.Params) {
          return mergeFrom((sentinel.provider.v1.ParamsOuterClass.Params)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(sentinel.provider.v1.ParamsOuterClass.Params other) {
        if (other == sentinel.provider.v1.ParamsOuterClass.Params.getDefaultInstance()) return this;
        if (other.hasDeposit()) {
          mergeDeposit(other.getDeposit());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        sentinel.provider.v1.ParamsOuterClass.Params parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (sentinel.provider.v1.ParamsOuterClass.Params) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private cosmos.base.v1beta1.CoinOuterClass.Coin deposit_;
      private com.google.protobuf.SingleFieldBuilderV3<
          cosmos.base.v1beta1.CoinOuterClass.Coin, cosmos.base.v1beta1.CoinOuterClass.Coin.Builder, cosmos.base.v1beta1.CoinOuterClass.CoinOrBuilder> depositBuilder_;
      /**
       * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
       * @return Whether the deposit field is set.
       */
      public boolean hasDeposit() {
        return depositBuilder_ != null || deposit_ != null;
      }
      /**
       * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
       * @return The deposit.
       */
      public cosmos.base.v1beta1.CoinOuterClass.Coin getDeposit() {
        if (depositBuilder_ == null) {
          return deposit_ == null ? cosmos.base.v1beta1.CoinOuterClass.Coin.getDefaultInstance() : deposit_;
        } else {
          return depositBuilder_.getMessage();
        }
      }
      /**
       * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
       */
      public Builder setDeposit(cosmos.base.v1beta1.CoinOuterClass.Coin value) {
        if (depositBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          deposit_ = value;
          onChanged();
        } else {
          depositBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
       */
      public Builder setDeposit(
          cosmos.base.v1beta1.CoinOuterClass.Coin.Builder builderForValue) {
        if (depositBuilder_ == null) {
          deposit_ = builderForValue.build();
          onChanged();
        } else {
          depositBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
       */
      public Builder mergeDeposit(cosmos.base.v1beta1.CoinOuterClass.Coin value) {
        if (depositBuilder_ == null) {
          if (deposit_ != null) {
            deposit_ =
              cosmos.base.v1beta1.CoinOuterClass.Coin.newBuilder(deposit_).mergeFrom(value).buildPartial();
          } else {
            deposit_ = value;
          }
          onChanged();
        } else {
          depositBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
       */
      public Builder clearDeposit() {
        if (depositBuilder_ == null) {
          deposit_ = null;
          onChanged();
        } else {
          deposit_ = null;
          depositBuilder_ = null;
        }

        return this;
      }
      /**
       * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
       */
      public cosmos.base.v1beta1.CoinOuterClass.Coin.Builder getDepositBuilder() {
        
        onChanged();
        return getDepositFieldBuilder().getBuilder();
      }
      /**
       * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
       */
      public cosmos.base.v1beta1.CoinOuterClass.CoinOrBuilder getDepositOrBuilder() {
        if (depositBuilder_ != null) {
          return depositBuilder_.getMessageOrBuilder();
        } else {
          return deposit_ == null ?
              cosmos.base.v1beta1.CoinOuterClass.Coin.getDefaultInstance() : deposit_;
        }
      }
      /**
       * <code>.cosmos.base.v1beta1.Coin deposit = 1 [(.gogoproto.nullable) = false];</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          cosmos.base.v1beta1.CoinOuterClass.Coin, cosmos.base.v1beta1.CoinOuterClass.Coin.Builder, cosmos.base.v1beta1.CoinOuterClass.CoinOrBuilder> 
          getDepositFieldBuilder() {
        if (depositBuilder_ == null) {
          depositBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              cosmos.base.v1beta1.CoinOuterClass.Coin, cosmos.base.v1beta1.CoinOuterClass.Coin.Builder, cosmos.base.v1beta1.CoinOuterClass.CoinOrBuilder>(
                  getDeposit(),
                  getParentForChildren(),
                  isClean());
          deposit_ = null;
        }
        return depositBuilder_;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:sentinel.provider.v1.Params)
    }

    // @@protoc_insertion_point(class_scope:sentinel.provider.v1.Params)
    private static final sentinel.provider.v1.ParamsOuterClass.Params DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new sentinel.provider.v1.ParamsOuterClass.Params();
    }

    public static sentinel.provider.v1.ParamsOuterClass.Params getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Params>
        PARSER = new com.google.protobuf.AbstractParser<Params>() {
      @java.lang.Override
      public Params parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Params(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Params> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Params> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public sentinel.provider.v1.ParamsOuterClass.Params getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_sentinel_provider_v1_Params_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_sentinel_provider_v1_Params_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n!sentinel/provider/v1/params.proto\022\024sen" +
      "tinel.provider.v1\032\024gogoproto/gogo.proto\032" +
      "\036cosmos/base/v1beta1/coin.proto\":\n\006Param" +
      "s\0220\n\007deposit\030\001 \001(\0132\031.cosmos.base.v1beta1" +
      ".CoinB\004\310\336\037\000B;Z1github.com/sentinel-offic" +
      "ial/hub/x/provider/types\250\342\036\000\310\341\036\000b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf2.GoGoProtos.getDescriptor(),
          cosmos.base.v1beta1.CoinOuterClass.getDescriptor(),
        });
    internal_static_sentinel_provider_v1_Params_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_sentinel_provider_v1_Params_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_sentinel_provider_v1_Params_descriptor,
        new java.lang.String[] { "Deposit", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.google.protobuf2.GoGoProtos.equalAll);
    registry.add(com.google.protobuf2.GoGoProtos.goprotoGettersAll);
    registry.add(com.google.protobuf2.GoGoProtos.nullable);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.google.protobuf2.GoGoProtos.getDescriptor();
    cosmos.base.v1beta1.CoinOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
