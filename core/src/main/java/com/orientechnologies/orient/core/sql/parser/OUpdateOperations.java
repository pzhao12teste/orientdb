/* Generated By:JJTree: Do not edit this line. OUpdateOperations.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.orientechnologies.orient.core.sql.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OUpdateOperations extends SimpleNode {
  protected static final int           TYPE_SET             = 0;
  protected static final int           TYPE_PUT             = 1;
  protected static final int           TYPE_MERGE           = 2;
  protected static final int           TYPE_CONTENT         = 3;
  protected static final int           TYPE_INCREMENT       = 4;
  protected static final int           TYPE_ADD             = 5;
  protected static final int           TYPE_REMOVE          = 6;

  protected int                        type;

  protected List<OUpdateItem>          updateItems          = new ArrayList<OUpdateItem>();

  protected List<OUpdatePutItem>       updatePutItems       = new ArrayList<OUpdatePutItem>();

  protected OJson                      json;

  protected List<OUpdateIncrementItem> updateIncrementItems = new ArrayList<OUpdateIncrementItem>();

  protected List<OUpdateRemoveItem>    updateRemoveItems    = new ArrayList<OUpdateRemoveItem>();

  public OUpdateOperations(int id) {
    super(id);
  }

  public OUpdateOperations(OrientSql p, int id) {
    super(p, id);
  }

  /** Accept the visitor. **/
  public Object jjtAccept(OrientSqlVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    boolean first = true;
    switch (type) {
    case TYPE_SET:
      result.append("SET ");
      for (OUpdateItem item : this.updateItems) {
        if (!first) {
          result.append(", ");
        }
        result.append(item);
        first = false;
      }
      break;
    case TYPE_PUT:
      result.append("PUT ");
      for (OUpdatePutItem item : this.updatePutItems) {
        if (!first) {
          result.append(", ");
        }
        result.append(item);
        first = false;
      }
      break;
    case TYPE_MERGE:
      result.append("MERGE ");
      result.append(json.toString());
      break;
    case TYPE_CONTENT:
      result.append("CONTENT ");
      result.append(json.toString());
      break;
    case TYPE_INCREMENT:
      result.append("INCREMENT ");
      for (OUpdateIncrementItem item : this.updateIncrementItems) {
        if (!first) {
          result.append(", ");
        }
        result.append(item);
        first = false;
      }
      break;
    case TYPE_ADD:
      result.append("ADD ");
      for (OUpdateIncrementItem item : this.updateIncrementItems) {
        if (!first) {
          result.append(", ");
        }
        result.append(item);
        first = false;
      }
      break;
    case TYPE_REMOVE:
      result.append("REMOVE ");
      for (OUpdateRemoveItem item : this.updateRemoveItems) {
        if (!first) {
          result.append(", ");
        }
        result.append(item);
        first = false;
      }
      break;

    }
    return result.toString();
  }

  public void replaceParameters(Map<Object, Object> params) {
    for (OUpdateItem item : updateItems) {
      item.replaceParameters(params);
    }
    for (OUpdatePutItem item : updatePutItems) {
      item.replaceParameters(params);
    }
    for (OUpdateIncrementItem item : updateIncrementItems) {
      item.replaceParameters(params);
    }
    for (OUpdateRemoveItem item : updateRemoveItems) {
      item.replaceParameters(params);
    }
    if (json != null) {
      json.replaceParameters(params);
    }

  }
}
/* JavaCC - OriginalChecksum=0eca3b3e4e3d96c42db57b7cd89cf755 (do not edit this line) */
