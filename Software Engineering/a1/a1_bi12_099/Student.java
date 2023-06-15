package a1_bi12_099;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;
import java.lang.Comparable;
import java.lang.String;

/**
 * @overview 
 * Student are people which study in a school or a university
 * Have 2 specific types: UndergradStudent and PostgradStudent
 * 
 * @attributes 
 * id    Integer
 * name  String
 * phoneNumber String
 * address String   
 * 
 * @object A typical Student is c=<m,n,p,q>, where id(m), name(n), phoneNumber(p), address(q).
 * 
 * @abstract_properties
 * mutable(id)=false /\ optional(id)=false /\ min(id)=1 /\ max(id)=pow(10, 9) 
 * mutable(name)=true /\ optional(name)=false /\ length(name)=50
 * mutable(phoneNumber)=true /\ optional(phoneNumber)=false /\ length(phoneNumber)=10
 * mutable(address)=true /\ optional(address)=false /\ length(address)=100
 * 
 * @author
 * BI12-099 Nguyen Thanh Duc
 * 
 */

public class Student implements Comparable<Student> {
    private static final int MIN_ID = 1;
    private static final int MAX_ID = 1000000000;
    private static final int LENGTH_NAME = 50;
    private static final int LENGTH_PHONE_NUMBER = 10;
    private static final int LENGTH_ADDRESS = 100;

    @DomainConstraint(type = "Integer", mutable = false, optional = false, min = MIN_ID, max = MAX_ID)
    private int id;

    @DomainConstraint(type = "String", optional = false, length = LENGTH_NAME)
    private String name;

    @DomainConstraint(type = "String", optional = false, length = LENGTH_PHONE_NUMBER)
    private String phoneNumber;

    @DomainConstraint(type = "String", optional = false, length = LENGTH_ADDRESS)
    private String address;
    
    /**
     * Constructor
	 * @effect <pre>
	 * 			if id, name, phoneNumber, address are valid
	 * 				initialize this as Student:<id, name, phoneNumber, address?
	 * 			else
	 * 				throws NotPossibleException
	 * 			</pre>
	 */
    public Student(@AttrRef("id") int id, @AttrRef("name") String name,
        @AttrRef("phoneNumber") String phoneNumber, @AttrRef("address") String address) throws NotPossibleException {
        // if id, name, phoneNumber, address are valid
        if (!validateId(id)) {
            throw new NotPossibleException("Student.init: Invalid Student id: " + id);
        }
    
        if (!validateName(name)) {
            throw new NotPossibleException("Student.init: Invalid Student name: " + name);
        }

        if (!validatephoneNumber(phoneNumber)) {
			throw new NotPossibleException("Student.init: Invalid phone number: " + phoneNumber);
		}

		if (!validateAddress(address)) {
			throw new NotPossibleException("Student.init: Invalid address: " + address);
		} 
    
        // initialise this as <id,name,phoneNumber,address>
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    // methods
    // @effects return <tt>this.name</tt>
    @DOpt(type=OptType.Observer) @AttrRef("name")
    public String getName() {
        return name;
    }
    
    /**
	 * @effects <pre>
	 * 			if name is valid
	 * 				set this.name = name
	 * 			else
	 * 				throw NotPossibleException
	 */
    @DOpt(type=OptType.Mutator) 
    @AttrRef("name")
    public boolean setName(String name) {
        if (validateName(name)) {
            this.name = name;
            return true;
        } else {
            return false;
        }
    }
    
    // @effects return <tt>this.phoneNumber</tt>
    @DOpt(type=OptType.Observer) @AttrRef("phoneNumber")
    public String getphoneNumber() {
        return phoneNumber;
    }
    
    /**
	 * @effects <pre>
	 * 			if phoneNumber is valid
	 * 				set this.phoneNumber = name
	 * 			else
	 * 				throw NotPossibleException
	 */
    @DOpt(type=OptType.Mutator) 
    @AttrRef("phoneNumber")
    public boolean setphoneNumber(String phoneNumber) {
        if (validatephoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
            return true;
        } else {
            return false;
        }
    }
    
    // @effects return <tt>this.address</tt>
    @DOpt(type=OptType.Observer) @AttrRef("address")
    public String getAddress() {
        return address;
    }
    
    /**
	 * @effects <pre>
	 * 			if address is valid
	 * 				set this.address = address
	 * 			else
	 * 				throw NotPossibleException
	 */
    @DOpt(type=OptType.Mutator) 
    @AttrRef("address")
    public boolean setAddress(String address) {
        if (validateAddress(address)) {
            this.address = address;
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * @effects return <tt>this.id</tt>
     */
    @DOpt(type=OptType.Observer) @AttrRef("id")
    public int getId() {
        return id;
    }

    /**
     * @effects <pre>
     *          if id is valid 
     *             return true 
     *          else
     *             return false</pre>
     */
    protected boolean validateId(int id) {
        if (id < MIN_ID || id > MAX_ID) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * @effects <pre>
     *          if name is valid
     *              set this.name=name
     *              return true
     *          else
     *              return false</pre>
     */
    private boolean validateName(String name) {
        if (name == null && name.length() == 0 && name.length() > LENGTH_NAME) {
            return false;
        }
        return true;
    }
    
    /**
	 * @effects <pre>
	 * 			if phoneNumber is valid
	 * 				return true
	 * 			else
	 * 				return false 
	 * 			</pre>
	 */
    private boolean validatephoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() == 0) {
            return false;
        }

        if (phoneNumber.length() > LENGTH_PHONE_NUMBER) {
            return false;
        }
        return true;
    }
    
    /**
	 * @effects <pre>
	 * 			if address is valid
	 * 				return true
	 * 			else
	 * 				return false 
	 * 			</pre>
	 */
    private boolean validateAddress(String address) {
        if (address == null || address.length() == 0) {
            return false;
        }

        if (address.length() > LENGTH_ADDRESS) {
            return false;
        }
        return true;
    }
    
    /**
	 * @effects <pre>
	 * 			if this satisfies rep invariant
	 * 				return true
	 * 			else
	 * 				return false
	 */
    public boolean repOk() {
        return validateId(id) && validateName(name) && validatephoneNumber(phoneNumber) && validateAddress(address); 
    }
    
    /**
	 * @effects <pre>
	 * 			return a string representation of this object
	 * 			</pre>
	 */
    @Override
    public String toString() {
        return String.format("Student: <%d, %s, %s, %s>",this.getId(),this.getName(),this.getphoneNumber(),this.getAddress());
    }
    
    /**
     * @effects <pre>
     *            if o is null 
     *              throws NullPointerException 
     *            else if o is not a Student object
     *              throws ClassCastException
     *            else 
     *              returns this.name.compareTo(o.name)
     *          </pre>
     */
    @Override
	public int compareTo(Student o) throws NullPointerException, ClassCastException {
			if (o == null) 
				throw new NullPointerException("Student.compareById");
			else if (!(o instanceof Student))
				throw new ClassCastException("Student.compareById: Not a Student" + o);
			
			Student student = (Student) o;
			return this.name.compareTo(student.name);
	}
}