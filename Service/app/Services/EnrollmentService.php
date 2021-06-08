<?php
namespace App\Services;

use App\Models\AssignmentRequest;
use App\Models\Enrollment;
use Illuminate\Support\Facades\DB;

class EnrollmentService {
    public function __construct() {

    }

    public function  getAll() {

            $enrollment = DB::table('Enrollment')->select(
               'Class.ClassName',
                'Trainee.Name',
                'Class.ClassID',
                'TraineeID'
            )->join('Class', 'Class.ClassID', '=', 'Enrollment.ClassID')
            ->join('Trainee','Trainee.UserName','=','Enrollment.TraineeID')
            ->get();
    
            return json_encode($enrollment);
        
    }
}