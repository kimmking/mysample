package com.pingplusplus.model;

import com.google.gson.*;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.net.APIResource;

import java.lang.reflect.Type;
import java.util.Map;

public class RedEnvelope extends APIResource {
  public static final Gson PRETTY_PRINT_GSON = new GsonBuilder().
          setPrettyPrinting().
          serializeNulls().
          disableHtmlEscaping().
          setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).
          setLongSerializationPolicy(LongSerializationPolicy.STRING).
          registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
            @Override
            public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
              if (src == src.longValue())
                return new JsonPrimitive(src.longValue());
              return new JsonPrimitive(src);
            }
          }).
          create();
  String id;
  String object;
  Long created;
  Boolean livemode;
  Boolean paid;
  Object app;
  String channel;
  String orderNo;
  Integer amount;
  String currency;
  String recipient;
  String subject;
  String body;
  String description;
  Map<String, String> extra;

  public static RedEnvelope create(Map<String, Object> params)
          throws AuthenticationException, InvalidRequestException,
          APIConnectionException, APIException {
    return create(params, null);
  }

  public static RedEnvelope retrieve(String id) throws AuthenticationException,
          InvalidRequestException, APIConnectionException,
          APIException {
    return retrieve(id, null, null);
  }

  public static RedEnvelope retrieve(String id, Map<String, Object> params) throws AuthenticationException,
          InvalidRequestException, APIConnectionException,
          APIException {
    return retrieve(id, params, null);
  }

  public static RedEnvelopeCollection all(Map<String, Object> params)
          throws AuthenticationException, InvalidRequestException,
          APIConnectionException, APIException {
    return all(params, null);
  }

  public static RedEnvelope create(Map<String, Object> params, String apiKey)
          throws AuthenticationException, InvalidRequestException,
          APIConnectionException, APIException {
    return request(RequestMethod.POST, classURL(RedEnvelope.class), params,
            RedEnvelope.class, apiKey);
  }

  public static RedEnvelope retrieve(String id, Map<String, Object> params, String apiKey)
          throws AuthenticationException, InvalidRequestException,
          APIConnectionException, APIException {
    return request(RequestMethod.GET, instanceURL(RedEnvelope.class, id), params,
            RedEnvelope.class, apiKey);
  }

  public static RedEnvelopeCollection all(Map<String, Object> params, String apiKey)
          throws AuthenticationException, InvalidRequestException,
          APIConnectionException, APIException {
    return request(RequestMethod.GET, classURL(RedEnvelope.class), params,
            RedEnvelopeCollection.class, apiKey);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public Long getCreated() {
    return created;
  }

  public void setCreated(Long created) {
    this.created = created;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getRecipient() {
    return recipient;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  public Boolean getLivemode() {
    return livemode;
  }

  public void setLivemode(Boolean livemode) {
    this.livemode = livemode;
  }

  public Boolean getPaid() {
    return paid;
  }

  public void setPaid(Boolean paid) {
    this.paid = paid;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getObject() {
    return object;
  }

  public void setObject(String object) {
    this.object = object;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public Object getApp() {
    return app;
  }

  public void setApp(Object app) {
    this.app = app;
  }

  public Map<String, String> getExtra() {
    return extra;
  }

  public void setExtra(Map<String, String> extra) {
    this.extra = extra;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public RedEnvelope update(Map<String, Object> params)
          throws AuthenticationException, InvalidRequestException,
          APIConnectionException, APIException {
    return update(params, null);
  }

  public RedEnvelope update(Map<String, Object> params, String apiKey)
          throws AuthenticationException, InvalidRequestException,
          APIConnectionException, APIException {
    return request(RequestMethod.POST, instanceURL(RedEnvelope.class, id), params,
            RedEnvelope.class, apiKey);
  }
}
