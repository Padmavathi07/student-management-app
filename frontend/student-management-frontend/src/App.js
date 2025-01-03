import React, { useState, useEffect } from "react";
import StudentService from "./services/StudentService";
import "./App.css";

function App() {
  const [students, setStudents] = useState([]);
  const [student, setStudent] = useState({
    name: "",
    age: "",
    studentClass: "",
    phoneNumber: "",
  });
  const [isEditing, setIsEditing] = useState(false);
  const [currentStudentId, setCurrentStudentId] = useState(null);

  useEffect(() => {
    fetchStudents();
  }, []);

  const fetchStudents = async () => {
    try {
      const response = await StudentService.getAllStudents();
      setStudents(response.data);
    } catch (error) {
      console.error("Error fetching students:", error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setStudent((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const addStudent = async () => {
    try {
      await StudentService.addStudent(student);
      fetchStudents(); // Refresh the table data
      setStudent({ name: "", age: "", studentClass: "", phoneNumber: "" }); // Clear input fields
    } catch (error) {
      console.error("Error adding student:", error);
    }
  };

  const updateStudent = async () => {
    try {
      await StudentService.updateStudent(currentStudentId, student);
      fetchStudents();
      setIsEditing(false);
      setStudent({ name: "", age: "", studentClass: "", phoneNumber: "" }); // Clear input fields
    } catch (error) {
      console.error("Error updating student:", error);
    }
  };

  const editStudent = (student) => {
    setIsEditing(true);
    setCurrentStudentId(student.id);
    setStudent({
      name: student.name,
      age: student.age,
      studentClass: student.studentClass,
      phoneNumber: student.phoneNumber,
    });
  };

  const deleteStudent = async (id) => {
    try {
      await StudentService.deleteStudent(id);
      fetchStudents();
    } catch (error) {
      console.error("Error deleting student:", error);
    }
  };

  return (
    <div className="App">
      <div className="container">
        <h1>Student Management System</h1>
        <div>
          <input
            type="text"
            name="name"
            placeholder="Name"
            value={student.name}
            onChange={handleChange}
          />
          <input
            type="text"
            name="age"
            placeholder="Age"
            value={student.age}
            onChange={handleChange}
          />
          <input
            type="text"
            name="studentClass"
            placeholder="Class"
            value={student.studentClass}
            onChange={handleChange}
          />
          <input
            type="text"
            name="phoneNumber"
            placeholder="Phone Number"
            value={student.phoneNumber}
            onChange={handleChange}
          />
          {isEditing ? (
            <button onClick={updateStudent}>Update</button>
          ) : (
            <button onClick={addStudent}>Add</button>
          )}
        </div>
        <div className="table-container">
          <table border="1">
            <thead>
              <tr>
                <th>Name</th>
                <th>Age</th>
                <th>Class</th>
                <th>Phone Number</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {students.map((student) => (
                <tr key={student.id}>
                  <td>{student.name}</td>
                  <td>{student.age}</td>
                  <td>{student.studentClass}</td>
                  <td>{student.phoneNumber}</td>
                  <td>
                    <button onClick={() => editStudent(student)}>Edit</button>
                    <button onClick={() => deleteStudent(student.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default App;
