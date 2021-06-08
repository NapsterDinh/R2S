<?php
namespace App\Services;

use App\Models\Answer;
use Illuminate\Support\Facades\DB;

class Statistic {
    public function __construct() {

    }

    public function showOverview($isAdmin, $idUSer, $idClass, $idModule) {
        if($role=='Admin')
        {
            $statistic = DB::table('Answer')
            ->select('ClassID', 'ModuleID', 'TraineeID', 'QuestionID', 'value')
            ->get();
    
            return json_encode($assignment);
        }
        else
        {
            //trainer
            $statistic = DB::table('Assignment')
                    ->select('Module.ModuleName as moduleName', 'Class.ClassName as className', 'Name as trainerName', 'RegistrationCode as registrationCode')
                    ->join('Trainer', 'Assignment.TrainerID', '=', 'Trainer.UserName')
                    ->join('Class', 'Assignment.ClassID', '=', 'Class.ClassID')
                    ->join('Module', 'Assignment.ModuleID', '=', 'Module.ModuleID')
                    ->where('Assignment.TrainerID', '=', $idUser)
                    ->get();

            return json_encode($assignment);
        }
        
    }
}