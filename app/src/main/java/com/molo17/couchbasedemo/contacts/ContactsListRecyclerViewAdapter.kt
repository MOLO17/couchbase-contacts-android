package com.molo17.couchbasedemo.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.molo17.couchbasedemo.R
import kotlinx.android.synthetic.main.activity_contact.view.*

/**
 * Created by Matteo Sist on 28/02/2019.
 */
class ContactsListRecyclerViewAdapter : RecyclerView.Adapter<ContactsListRecyclerViewAdapter.ViewHolder>() {

    /// STEP 10
    /// Declare contacts list and init it empty.
//    var contacts: List<Contact> = emptyList()
    var onContactClickListener: ((contactId: String) -> Unit)? = null
    var onContactLongClickListener: ((contactId: String) -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_contact, parent, false)
        return ViewHolder(view).apply {

            /// STEP 32
            /// Register click action to navigate to detail activity.
//            itemView.setOnClickListener { onContactClickListener?.invoke(contacts[adapterPosition].id) }

            /// STEP 39
            /// Register long click action to delete document
//            itemView.setOnLongClickListener { onContactLongClickListener?.invoke(contacts[adapterPosition].id); true }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        /// STEP 15
        /// Retrieve contact from datasource and fill in the fields of cell.
//        val item = contacts[position]
//        holder.nameSurname.text = makeNameSurname(item)
//        holder.phoneNumber.text = makePhoneNumber(item)
    }

    /// STEP 11
    /// Return number of item recycler view.
    override fun getItemCount(): Int = 0 // Remove when uncomment below line
//    override fun getItemCount(): Int = contacts.size

    /// STEP 12
    /// Return item id.
//    override fun getItemId(position: Int): Long = contacts[position].id.hashCode().toLong()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameSurname: TextView = view.nameSurnameTextView
        val phoneNumber: TextView = view.phoneNumberTextView
    }

    /// STEP 14
    /// Utilities functions to retrieve phone number and name surname from Contact object.
//    private fun makeNameSurname(contact: Contact): String {
//        return listOfNotNull(contact.name, contact.surname).run {
//            if (isEmpty()) "No name"
//            else joinToString(" ")
//        }
//    }
//
//    private fun makePhoneNumber(contact: Contact): String {
//        return contact.phoneNumber?.takeIf(String::isNotBlank) ?: "No phone number"
//    }
}