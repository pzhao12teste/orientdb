/* Generated By:JJTree: Do not edit this line. OInputParameter.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.orientechnologies.orient.core.sql.parser;

import com.orientechnologies.orient.core.db.record.OIdentifiable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class OInputParameter extends SimpleNode {

  protected String     dateFormatString = "yyyy-MM-dd HH:mm:ss.SSS";
  protected DateFormat dateFormat       = new SimpleDateFormat(dateFormatString);

  public OInputParameter(int id) {
    super(id);
  }

  public OInputParameter(OrientSql p, int id) {
    super(p, id);
  }

  /** Accept the visitor. **/
  public Object jjtAccept(OrientSqlVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  public Object bindFromInputParams(Map<Object, Object> params) {
    return null;
  }

  protected Object toParsedTree(Object value) {
    if (value == null) {
      return null;
    }
    if(value instanceof Boolean){
      return value;
    }
    if (value instanceof Integer) {
      OInteger result = new OInteger(-1);
      result.setValue((Integer) value);
      return result;
    }
    if (value instanceof Number) {
      OFloatingPoint result = new OFloatingPoint(-1);
      result.sign = ((Number) value).doubleValue() >= 0 ? 1 : -1;
      result.stringValue = value.toString();
      if (result.stringValue.startsWith("-")) {
        result.stringValue = result.stringValue.substring(1);
      }
      return result;
    }
    if (value instanceof String) {
      return value;
    }
    if (value instanceof Collection) {
      OCollection coll = new OCollection(-1);
      coll.expressions = new ArrayList<OExpression>();
      for (Object o : (Collection) value) {
        OExpression exp = new OExpression(-1);
        exp.value = toParsedTree(o);
        coll.expressions.add(exp);
      }
      return coll;
    }
    if (value instanceof OIdentifiable) {
      // TODO if invalid build a JSON
      ORid rid = new ORid(-1);
      String stringVal = ((OIdentifiable) value).getIdentity().toString().substring(1);
      String[] splitted = stringVal.split(":");
      OInteger c = new OInteger(-1);
      c.setValue(Integer.parseInt(splitted[0]));
      rid.cluster = c;
      OInteger p = new OInteger(-1);
      p.setValue(Integer.parseInt(splitted[1]));
      rid.position = p;
      return rid;
    }
    if (value instanceof Date) {
      OFunctionCall function = new OFunctionCall(-1);
      function.name = new OIdentifier(-1);
      function.name.value = "date";

      OExpression dateExpr = new OExpression(-1);
      dateExpr.singleQuotes = true;
      dateExpr.doubleQuotes = false;
      dateExpr.value = dateFormat.format(value);
      function.getParams().add(dateExpr);

      OExpression dateFormatExpr = new OExpression(-1);
      dateFormatExpr.singleQuotes = true;
      dateFormatExpr.doubleQuotes = false;
      dateFormatExpr.value = dateFormatString;
      function.getParams().add(dateFormatExpr);
      return function;
    }

    return this;
  }

}
/* JavaCC - OriginalChecksum=bb2f3732f5e3be4d954527ee0baa9020 (do not edit this line) */
