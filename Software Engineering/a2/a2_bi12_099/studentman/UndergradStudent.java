package a2_bi12_099.studentman;

import a2_bi12_099.kengine.Doc;
import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview
 * UndergradStudent is subclass of class Student 
 *  
 * @object
 * A typical UndergradStudent is c=<m,n,p,q>, where id(m), name(n), phoneNumber(p), address(q).
 *  
 * @abstract_properties
 * min(id)= 1e5 /\ max(id)= 1e8
 * 
 * @author
 * BI12-099 Nguyen Thanh Duc
 * 
 */ 
public class UndergradStudent extends Student { 

    private static final int MIN_ID = 100000;
    private static final int MAX_ID = 100000000;
    
	// constructor
    /**
	 * @effect <pre>
	 * 			if id, name, phoneNumber, address are valid
	 * 				initialize this as UndergradStudent:<id, name, phoneNumber, address>
	 * 			else
	 * 				throws NotPossibleException
	 * 			</pre>
	 */
    public UndergradStudent(
		        @AttrRef("id") int id, @AttrRef("name") String name, 
	        	@AttrRef("phoneNumber") String phoneNumber, @AttrRef("address") String address) throws NotPossibleException {
		super(id, name, phoneNumber, address);
	}
    
    /**
	 * @effects <pre>
	 * 			return a string representation of this object
	 * 			</pre>
	 */
    public String toString() {
        return String.format("Undergrad student: <%d, %s, %s, %s>",this.getId(),this.getName(),this.getPhoneNumber(),this.getAddress());
    }
    
    /**
	 * @effects <pre>
	 * 			if id is valid
	 * 				return true
	 * 			else
	 * 				return false
	 * 			</pre>
	 */
	@DomainConstraint(type="Integer", mutable=false, optional=false, min=MIN_ID, max=MAX_ID)
    protected boolean validateID(int id) {
        if (id < MIN_ID || id > MAX_ID) {
            return false;
        } else {
            return true;
        }
    }

	/**
     * @effects <pre>
     *      return a simple HTML document generated from the data
     *      </pre>
     */
    public String toHtmlDoc() {
        return "<html>\n" +
                "<head><title>PostgradStudent:" + getId() + "-" + getName() + "</title></head>\n" +"<body>\n" +
                getId() + " " + getName() + " " + getPhoneNumber() + " " + getAddress() + "\n" +
                "</body></html>";
    }
}