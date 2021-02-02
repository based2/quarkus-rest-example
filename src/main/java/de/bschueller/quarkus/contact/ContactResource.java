package de.bschueller.quarkus.contact;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class ContactResource {
    private static final Logger LOG = Logger.getLogger(ContactResource.class);

    @Inject
    EntityManager entityManager;

    static boolean isException = false;
    static int counter = 0;

    private void generateException(){
        if (isException) {
            counter++;
            try {
                throw new Exception("# "+counter);
            } catch( Exception e ) {
                LOG.error( "A generated exception occuredr.", e );
            }
        } else {
            isException = true;
        }
    }

    public List<Contact> getContacts() {
        List<Contact> contacts = entityManager.createQuery("SELECT c FROM Contact c").getResultList();
        LOG.debug( "size:" + contacts.size());
        generateException();
        return contacts;
    }

    public Contact getContact(Long id) {
        LOG.info( "id:" + id);
        generateException();
        return entityManager.find(Contact.class, id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Contact addContact(Contact contact) {
        LOG.info( "contact:" + contact.toString());
        generateException();
        entityManager.persist(contact);
        return contact;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void updateContact(Long id, Contact contact) {
        LOG.info( "contact:" + contact.toString());
        generateException();
        Contact contactToUpdate = entityManager.find(Contact.class, id);
        if (null != contactToUpdate) {
            contactToUpdate.setFirstName(contact.getFirstName());
            contactToUpdate.setLastName(contact.getLastName());
            contactToUpdate.setCompanyName(contact.getCompanyName());
        } else {
            throw new RuntimeException("No such contact available");
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteContact(Long id) {
        LOG.info( "id:" + id);
        generateException();
        Contact contact = getContact(id);
        entityManager.remove(contact);
    }
}
