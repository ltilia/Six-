package org.json.simple.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONParser {
    public static final int S_END = 6;
    public static final int S_INIT = 0;
    public static final int S_IN_ARRAY = 3;
    public static final int S_IN_ERROR = -1;
    public static final int S_IN_FINISHED_VALUE = 1;
    public static final int S_IN_OBJECT = 2;
    public static final int S_IN_PAIR_VALUE = 5;
    public static final int S_PASSED_PAIR_KEY = 4;
    private LinkedList handlerStatusStack;
    private Yylex lexer;
    private int status;
    private Yytoken token;

    public JSONParser() {
        this.lexer = new Yylex((Reader) null);
        this.token = null;
        this.status = S_INIT;
    }

    private int peekStatus(LinkedList statusStack) {
        if (statusStack.size() == 0) {
            return S_IN_ERROR;
        }
        return ((Integer) statusStack.getFirst()).intValue();
    }

    public void reset() {
        this.token = null;
        this.status = S_INIT;
        this.handlerStatusStack = null;
    }

    public void reset(Reader in) {
        this.lexer.yyreset(in);
        reset();
    }

    public int getPosition() {
        return this.lexer.getPosition();
    }

    public Object parse(String s) throws ParseException {
        return parse(s, (ContainerFactory) null);
    }

    public Object parse(String s, ContainerFactory containerFactory) throws ParseException {
        try {
            return parse(new StringReader(s), containerFactory);
        } catch (IOException ie) {
            throw new ParseException(S_IN_ERROR, S_IN_OBJECT, ie);
        }
    }

    public Object parse(Reader in) throws IOException, ParseException {
        return parse(in, (ContainerFactory) null);
    }

    public Object parse(Reader in, ContainerFactory containerFactory) throws IOException, ParseException {
        reset(in);
        LinkedList statusStack = new LinkedList();
        LinkedList valueStack = new LinkedList();
        do {
            nextToken();
            Map newObject;
            List newArray;
            switch (this.status) {
                case S_IN_ERROR /*-1*/:
                    throw new ParseException(getPosition(), S_IN_FINISHED_VALUE, this.token);
                case S_INIT /*0*/:
                    try {
                        switch (this.token.type) {
                            case S_INIT /*0*/:
                                this.status = S_IN_FINISHED_VALUE;
                                statusStack.addFirst(new Integer(this.status));
                                valueStack.addFirst(this.token.value);
                                break;
                            case S_IN_FINISHED_VALUE /*1*/:
                                this.status = S_IN_OBJECT;
                                statusStack.addFirst(new Integer(this.status));
                                valueStack.addFirst(createObjectContainer(containerFactory));
                                break;
                            case S_IN_ARRAY /*3*/:
                                this.status = S_IN_ARRAY;
                                statusStack.addFirst(new Integer(this.status));
                                valueStack.addFirst(createArrayContainer(containerFactory));
                                break;
                            default:
                                this.status = S_IN_ERROR;
                                break;
                        }
                    } catch (IOException ie) {
                        throw ie;
                    }
                case S_IN_FINISHED_VALUE /*1*/:
                    if (this.token.type == S_IN_ERROR) {
                        return valueStack.removeFirst();
                    }
                    throw new ParseException(getPosition(), S_IN_FINISHED_VALUE, this.token);
                case S_IN_OBJECT /*2*/:
                    switch (this.token.type) {
                        case S_INIT /*0*/:
                            if (!(this.token.value instanceof String)) {
                                this.status = S_IN_ERROR;
                                break;
                            }
                            valueStack.addFirst(this.token.value);
                            this.status = S_PASSED_PAIR_KEY;
                            statusStack.addFirst(new Integer(this.status));
                            break;
                        case S_IN_OBJECT /*2*/:
                            if (valueStack.size() <= S_IN_FINISHED_VALUE) {
                                this.status = S_IN_FINISHED_VALUE;
                                break;
                            }
                            statusStack.removeFirst();
                            valueStack.removeFirst();
                            this.status = peekStatus(statusStack);
                            break;
                        case S_IN_PAIR_VALUE /*5*/:
                            break;
                        default:
                            this.status = S_IN_ERROR;
                            break;
                    }
                case S_IN_ARRAY /*3*/:
                    List val;
                    switch (this.token.type) {
                        case S_INIT /*0*/:
                            ((List) valueStack.getFirst()).add(this.token.value);
                            break;
                        case S_IN_FINISHED_VALUE /*1*/:
                            val = (List) valueStack.getFirst();
                            newObject = createObjectContainer(containerFactory);
                            val.add(newObject);
                            this.status = S_IN_OBJECT;
                            statusStack.addFirst(new Integer(this.status));
                            valueStack.addFirst(newObject);
                            break;
                        case S_IN_ARRAY /*3*/:
                            val = (List) valueStack.getFirst();
                            newArray = createArrayContainer(containerFactory);
                            val.add(newArray);
                            this.status = S_IN_ARRAY;
                            statusStack.addFirst(new Integer(this.status));
                            valueStack.addFirst(newArray);
                            break;
                        case S_PASSED_PAIR_KEY /*4*/:
                            if (valueStack.size() <= S_IN_FINISHED_VALUE) {
                                this.status = S_IN_FINISHED_VALUE;
                                break;
                            }
                            statusStack.removeFirst();
                            valueStack.removeFirst();
                            this.status = peekStatus(statusStack);
                            break;
                        case S_IN_PAIR_VALUE /*5*/:
                            break;
                        default:
                            this.status = S_IN_ERROR;
                            break;
                    }
                case S_PASSED_PAIR_KEY /*4*/:
                    String key;
                    Map parent;
                    switch (this.token.type) {
                        case S_INIT /*0*/:
                            statusStack.removeFirst();
                            ((Map) valueStack.getFirst()).put((String) valueStack.removeFirst(), this.token.value);
                            this.status = peekStatus(statusStack);
                            break;
                        case S_IN_FINISHED_VALUE /*1*/:
                            statusStack.removeFirst();
                            key = (String) valueStack.removeFirst();
                            parent = (Map) valueStack.getFirst();
                            newObject = createObjectContainer(containerFactory);
                            parent.put(key, newObject);
                            this.status = S_IN_OBJECT;
                            statusStack.addFirst(new Integer(this.status));
                            valueStack.addFirst(newObject);
                            break;
                        case S_IN_ARRAY /*3*/:
                            statusStack.removeFirst();
                            key = (String) valueStack.removeFirst();
                            parent = (Map) valueStack.getFirst();
                            newArray = createArrayContainer(containerFactory);
                            parent.put(key, newArray);
                            this.status = S_IN_ARRAY;
                            statusStack.addFirst(new Integer(this.status));
                            valueStack.addFirst(newArray);
                            break;
                        case S_END /*6*/:
                            break;
                        default:
                            this.status = S_IN_ERROR;
                            break;
                    }
            }
            if (this.status == S_IN_ERROR) {
                throw new ParseException(getPosition(), S_IN_FINISHED_VALUE, this.token);
            }
        } while (this.token.type != S_IN_ERROR);
        throw new ParseException(getPosition(), S_IN_FINISHED_VALUE, this.token);
    }

    private void nextToken() throws ParseException, IOException {
        this.token = this.lexer.yylex();
        if (this.token == null) {
            this.token = new Yytoken(S_IN_ERROR, null);
        }
    }

    private Map createObjectContainer(ContainerFactory containerFactory) {
        if (containerFactory == null) {
            return new JSONObject();
        }
        Map m = containerFactory.createObjectContainer();
        if (m == null) {
            return new JSONObject();
        }
        return m;
    }

    private List createArrayContainer(ContainerFactory containerFactory) {
        if (containerFactory == null) {
            return new JSONArray();
        }
        List l = containerFactory.creatArrayContainer();
        if (l == null) {
            return new JSONArray();
        }
        return l;
    }

    public void parse(String s, ContentHandler contentHandler) throws ParseException {
        parse(s, contentHandler, false);
    }

    public void parse(String s, ContentHandler contentHandler, boolean isResume) throws ParseException {
        try {
            parse(new StringReader(s), contentHandler, isResume);
        } catch (IOException ie) {
            throw new ParseException(S_IN_ERROR, S_IN_OBJECT, ie);
        }
    }

    public void parse(Reader in, ContentHandler contentHandler) throws IOException, ParseException {
        parse(in, contentHandler, false);
    }

    public void parse(Reader in, ContentHandler contentHandler, boolean isResume) throws IOException, ParseException {
        if (!isResume) {
            reset(in);
            this.handlerStatusStack = new LinkedList();
        } else if (this.handlerStatusStack == null) {
            reset(in);
            this.handlerStatusStack = new LinkedList();
        }
        LinkedList statusStack = this.handlerStatusStack;
        do {
            try {
                switch (this.status) {
                    case S_IN_ERROR /*-1*/:
                        throw new ParseException(getPosition(), S_IN_FINISHED_VALUE, this.token);
                    case S_INIT /*0*/:
                        contentHandler.startJSON();
                        nextToken();
                        switch (this.token.type) {
                            case S_INIT /*0*/:
                                this.status = S_IN_FINISHED_VALUE;
                                statusStack.addFirst(new Integer(this.status));
                                if (!contentHandler.primitive(this.token.value)) {
                                    return;
                                }
                                break;
                            case S_IN_FINISHED_VALUE /*1*/:
                                this.status = S_IN_OBJECT;
                                statusStack.addFirst(new Integer(this.status));
                                if (!contentHandler.startObject()) {
                                    return;
                                }
                                break;
                            case S_IN_ARRAY /*3*/:
                                this.status = S_IN_ARRAY;
                                statusStack.addFirst(new Integer(this.status));
                                if (!contentHandler.startArray()) {
                                    return;
                                }
                                break;
                            default:
                                this.status = S_IN_ERROR;
                                break;
                        }
                    case S_IN_FINISHED_VALUE /*1*/:
                        nextToken();
                        if (this.token.type == S_IN_ERROR) {
                            contentHandler.endJSON();
                            this.status = S_END;
                            return;
                        }
                        this.status = S_IN_ERROR;
                        throw new ParseException(getPosition(), S_IN_FINISHED_VALUE, this.token);
                    case S_IN_OBJECT /*2*/:
                        nextToken();
                        switch (this.token.type) {
                            case S_INIT /*0*/:
                                if (!(this.token.value instanceof String)) {
                                    this.status = S_IN_ERROR;
                                    break;
                                }
                                String key = this.token.value;
                                this.status = S_PASSED_PAIR_KEY;
                                statusStack.addFirst(new Integer(this.status));
                                if (!contentHandler.startObjectEntry(key)) {
                                    return;
                                }
                                break;
                            case S_IN_OBJECT /*2*/:
                                if (statusStack.size() > S_IN_FINISHED_VALUE) {
                                    statusStack.removeFirst();
                                    this.status = peekStatus(statusStack);
                                } else {
                                    this.status = S_IN_FINISHED_VALUE;
                                }
                                if (!contentHandler.endObject()) {
                                    return;
                                }
                                break;
                            case S_IN_PAIR_VALUE /*5*/:
                                break;
                            default:
                                this.status = S_IN_ERROR;
                                break;
                        }
                    case S_IN_ARRAY /*3*/:
                        nextToken();
                        switch (this.token.type) {
                            case S_INIT /*0*/:
                                if (!contentHandler.primitive(this.token.value)) {
                                    return;
                                }
                                break;
                            case S_IN_FINISHED_VALUE /*1*/:
                                this.status = S_IN_OBJECT;
                                statusStack.addFirst(new Integer(this.status));
                                if (!contentHandler.startObject()) {
                                    return;
                                }
                                break;
                            case S_IN_ARRAY /*3*/:
                                this.status = S_IN_ARRAY;
                                statusStack.addFirst(new Integer(this.status));
                                if (!contentHandler.startArray()) {
                                    return;
                                }
                                break;
                            case S_PASSED_PAIR_KEY /*4*/:
                                if (statusStack.size() > S_IN_FINISHED_VALUE) {
                                    statusStack.removeFirst();
                                    this.status = peekStatus(statusStack);
                                } else {
                                    this.status = S_IN_FINISHED_VALUE;
                                }
                                if (!contentHandler.endArray()) {
                                    return;
                                }
                                break;
                            case S_IN_PAIR_VALUE /*5*/:
                                break;
                            default:
                                this.status = S_IN_ERROR;
                                break;
                        }
                    case S_PASSED_PAIR_KEY /*4*/:
                        nextToken();
                        switch (this.token.type) {
                            case S_INIT /*0*/:
                                statusStack.removeFirst();
                                this.status = peekStatus(statusStack);
                                if (!contentHandler.primitive(this.token.value)) {
                                    return;
                                }
                                if (!contentHandler.endObjectEntry()) {
                                    return;
                                }
                                break;
                            case S_IN_FINISHED_VALUE /*1*/:
                                statusStack.removeFirst();
                                statusStack.addFirst(new Integer(S_IN_PAIR_VALUE));
                                this.status = S_IN_OBJECT;
                                statusStack.addFirst(new Integer(this.status));
                                if (!contentHandler.startObject()) {
                                    return;
                                }
                                break;
                            case S_IN_ARRAY /*3*/:
                                statusStack.removeFirst();
                                statusStack.addFirst(new Integer(S_IN_PAIR_VALUE));
                                this.status = S_IN_ARRAY;
                                statusStack.addFirst(new Integer(this.status));
                                if (!contentHandler.startArray()) {
                                    return;
                                }
                                break;
                            case S_END /*6*/:
                                break;
                            default:
                                this.status = S_IN_ERROR;
                                break;
                        }
                    case S_IN_PAIR_VALUE /*5*/:
                        statusStack.removeFirst();
                        this.status = peekStatus(statusStack);
                        if (!contentHandler.endObjectEntry()) {
                            return;
                        }
                        break;
                    case S_END /*6*/:
                        return;
                }
                if (this.status == S_IN_ERROR) {
                    throw new ParseException(getPosition(), S_IN_FINISHED_VALUE, this.token);
                }
            } catch (IOException ie) {
                this.status = S_IN_ERROR;
                throw ie;
            } catch (ParseException pe) {
                this.status = S_IN_ERROR;
                throw pe;
            } catch (RuntimeException re) {
                this.status = S_IN_ERROR;
                throw re;
            } catch (Error e) {
                this.status = S_IN_ERROR;
                throw e;
            }
        } while (this.token.type != S_IN_ERROR);
        this.status = S_IN_ERROR;
        throw new ParseException(getPosition(), S_IN_FINISHED_VALUE, this.token);
    }
}
