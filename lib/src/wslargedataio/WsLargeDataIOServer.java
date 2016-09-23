package wslargedataio;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonServerMethod;
import us.kbase.common.service.JsonServerServlet;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.Tuple11;

//BEGIN_HEADER
import us.kbase.workspace.ProvenanceAction;
//END_HEADER

/**
 * <p>Original spec-file module name: WsLargeDataIO</p>
 * <pre>
 * A KBase module: WsLargeDataIO
 * </pre>
 */
public class WsLargeDataIOServer extends JsonServerServlet {
    private static final long serialVersionUID = 1L;
    private static final String version = "0.0.1";
    private static final String gitUrl = "https://github.com/rsutormin/WsLargeDataIO";
    private static final String gitCommitHash = "370bad8645bb3f977b323b77d110febe46cc3213";

    //BEGIN_CLASS_HEADER
    //END_CLASS_HEADER

    public WsLargeDataIOServer() throws Exception {
        super("WsLargeDataIO");
        //BEGIN_CONSTRUCTOR
        //END_CONSTRUCTOR
    }

    /**
     * <p>Original spec-file function name: save_objects</p>
     * <pre>
     * Save objects to the workspace. Saving over a deleted object undeletes
     * it.
     * </pre>
     * @param   params   instance of type {@link wslargedataio.SaveObjectsParams SaveObjectsParams}
     * @return   parameter "info" of list of original type "object_info" (Information about an object, including user provided metadata. objid - the numerical id of the object. name - the name of the object. type - the type of the object. save_date - the save date of the object. ver - the version of the object. saved_by - the user that saved or copied the object. wsid - the id of the workspace containing the object. workspace - the name of the workspace containing the object. chsum - the md5 checksum of the object. size - the size of the object in bytes. meta - arbitrary user-supplied metadata about the object.) &rarr; tuple of size 11: parameter "objid" of Long, parameter "name" of String, parameter "type" of String, parameter "save_date" of String, parameter "version" of Long, parameter "saved_by" of String, parameter "wsid" of Long, parameter "workspace" of String, parameter "chsum" of String, parameter "size" of Long, parameter "meta" of mapping from String to String
     */
    @JsonServerMethod(rpc = "WsLargeDataIO.save_objects", async=true)
    public List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> saveObjects(SaveObjectsParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        List<Tuple11<Long, String, String, String, Long, String, Long, String, String, Long, Map<String,String>>> returnVal = null;
        //BEGIN save_objects
        @SuppressWarnings("unchecked")
        List<ProvenanceAction> provenance = (List<ProvenanceAction>)jsonRpcContext.getProvenance();
        returnVal = WsLargeDataIOImpl.saveObjects(params, authPart, config, provenance);
        //END save_objects
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: get_objects</p>
     * <pre>
     * Get objects from the workspace.
     * </pre>
     * @param   params   instance of type {@link wslargedataio.GetObjectsParams GetObjectsParams}
     * @return   parameter "results" of type {@link wslargedataio.GetObjectsResults GetObjectsResults}
     */
    @JsonServerMethod(rpc = "WsLargeDataIO.get_objects", async=true)
    public GetObjectsResults getObjects(GetObjectsParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        GetObjectsResults returnVal = null;
        //BEGIN get_objects
        returnVal = WsLargeDataIOImpl.getObjects(params, authPart, config);
        //END get_objects
        return returnVal;
    }
    @JsonServerMethod(rpc = "WsLargeDataIO.status")
    public Map<String, Object> status() {
        Map<String, Object> returnVal = null;
        //BEGIN_STATUS
        returnVal = new LinkedHashMap<String, Object>();
        returnVal.put("state", "OK");
        returnVal.put("message", "");
        returnVal.put("version", version);
        returnVal.put("git_url", gitUrl);
        returnVal.put("git_commit_hash", gitCommitHash);
        //END_STATUS
        return returnVal;
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            new WsLargeDataIOServer().startupServer(Integer.parseInt(args[0]));
        } else if (args.length == 3) {
            JsonServerSyslog.setStaticUseSyslog(false);
            JsonServerSyslog.setStaticMlogFile(args[1] + ".log");
            new WsLargeDataIOServer().processRpcCall(new File(args[0]), new File(args[1]), args[2]);
        } else {
            System.out.println("Usage: <program> <server_port>");
            System.out.println("   or: <program> <context_json_file> <output_json_file> <token>");
            return;
        }
    }
}
