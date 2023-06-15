package a1_bi12_099;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview 
 * PostgradStudent is a subclass of class Student
 *
 * @attributes
 * gpa float
 *
 * @object 
 * A typical PostgradStudent is a=(m,n,p,q,r) where id(m), name(n), phoneNum(p), address(q), gpa(r)
 *
 * @abstract_properties
 * min(id)=100000001 /\ max(id)=1000000000
 * mutable(gpa)=true /\ optional(gpa)=false /\ min(gpa)=0.0 /\ max(gpa)=4.0
 * 
 * @author 
 * BI12-099 Nguyen Thanh Duc
 * 
 */

public class PostgradStudent extends Student {
    
    private static final int MIN_ID = 100000001;
    private static final int MAX_ID = 1000000000;
    private static final float MIN_GPA = 0.0f;
    private static final float MAX_GPA = 4.0f;
    
    @DomainConstraint(type = "Float", optional = false, min = MIN_GPA, max = MAX_GPA)
    private float gpa;
    
    // constructor methods
	/**
	 * @effect <pre>
	 * 			if id, name, phoneNumber, address, gpa are valid
	 * 				initialize this as PostgradStudent:<id, name, phoneNumber, address, gpa>
	 * 			else
	 * 				throws NotPossibleException
	 * 			</pre>
	 */

    public PostgradStudent(@AttrRef("id") int id, @AttrRef("name") String name,
			@AttrRef("phoneNum") String phoneNum, @AttrRef("address") String address,
			@AttrRef("gpa") float gpa) throws NotPossibleException {
		super(id,name,phoneNum,address);
		
		if (!validateGpa(gpa)) {
			throw new NotPossibleException("PostgradStudent.init: Invalid gpa: " + gpa);
		} else {
			this.gpa=gpa;
		}
	}
    
    // @effects return <tt>this.gpa</tt>
    @DOpt(type=OptType.Observer) @AttrRef("gpa")
    public float getGpa() {
        return gpa;
    }
    
    /**
	 * @effects <pre>
	 * 			if gpa is valid
	 * 				set this.gpa = gpa
	 * 				return true
	 * 			else
	 * 				return false
	 * 			</pre>
	 */
    public boolean setGpa(float gpa) {
        if (validateGpa(gpa)) {
            this.gpa = gpa;
            return true;
        } else {
            return false;
        }
    }
    
	@DomainConstraint(type="Integer", mutable=false, optional=false, min=MIN_ID, max=MAX_ID)
    protected boolean validateID(int id) {
        if (id < MIN_ID || id > MAX_ID) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validateGpa(float gpa) {
        if (gpa < MIN_GPA || gpa > MAX_GPA) {
            return false;
        } else {
            return true;
        }
    }
    
    public String toString() {
        return String.format("Postgrad student: <%d, %s, %s, %s, %f>", this.getId(), this.getName(), this.getPhoneNum(), this.getAddress(), this.getGpa());
    }
}