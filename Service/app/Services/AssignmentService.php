<?php
namespace App\Services;

use App\Models\Assignment;
use Illuminate\Support\Facades\DB;

class AssignmentService {
    public function __construct() {

    }

    public function showAllAssignment($idUser,$role) {
        if($role=='Admin')
        {
            $assignment = DB::table('Assignment')
            ->select('Module.ModuleName as moduleName', 'Class.ClassName as className', 'Name as trainerName', 'RegistrationCode as registrationCode')
            ->join('Trainer', 'Assignment.TrainerID', '=', 'Trainer.UserName')
            ->join('Class', 'Assignment.ClassID', '=', 'Class.ClassID')
            ->join('Module', 'Assignment.ModuleID', '=', 'Module.ModuleID')
            ->get();
    
            return json_encode($assignment);
        }
        else
        {
            //trainer
            $assignment = DB::table('Assignment')
                    ->select('Module.ModuleName as moduleName', 'Class.ClassName as className', 'Name as trainerName', 'RegistrationCode as registrationCode')
                    ->join('Trainer', 'Assignment.TrainerID', '=', 'Trainer.UserName')
                    ->join('Class', 'Assignment.ClassID', '=', 'Class.ClassID')
                    ->join('Module', 'Assignment.ModuleID', '=', 'Module.ModuleID')
                    ->where('Assignment.TrainerID', '=', $idUser)
                    ->get();

            return json_encode($assignment);
        }
        
    }

    public function searchAssignment($idUser,$role,$str_search) {

            if($str_search =="Empty")
            {
                if($role=='Admin')
                {
                    $assignment = DB::table('Assignment')
                    ->select('Module.ModuleName as moduleName', 'Class.ClassName as className', 'Name as trainerName', 'RegistrationCode as registrationCode')
                    ->join('Trainer', 'Assignment.TrainerID', '=', 'Trainer.UserName')
                    ->join('Class', 'Assignment.ClassID', '=', 'Class.ClassID')
                    ->join('Module', 'Assignment.ModuleID', '=', 'Module.ModuleID')
                    ->get();
            
                    return json_encode($assignment);
                }
                else
                {
                    //trainer
                    $assignment = DB::table('Assignment')
                            ->select('Module.ModuleName as moduleName', 'Class.ClassName as className', 'Name as trainerName', 'RegistrationCode as registrationCode')
                            ->join('Trainer', 'Assignment.TrainerID', '=', 'Trainer.UserName')
                            ->join('Class', 'Assignment.ClassID', '=', 'Class.ClassID')
                            ->join('Module', 'Assignment.ModuleID', '=', 'Module.ModuleID')
                            ->where('Assignment.TrainerID', '=', $idUser)
                            ->get();

                    return json_encode($assignment);
                }
            }
            else
            {
                if($role=='Admin')
                {
                    $assignment = DB::table('Assignment')
                                            ->select('Module.ModuleName as moduleName', 'Class.ClassName as className', 'Name as trainerName', 'RegistrationCode as registrationCode')
                                            ->join('Trainer', 'Assignment.TrainerID', '=', 'Trainer.UserName')
                                            ->join('Class', 'Assignment.ClassID', '=', 'Class.ClassID')
                                            ->join('Module', 'Assignment.ModuleID', '=', 'Module.ModuleID')
                                            ->where('ClassName','=' ,$str_search)
                                            ->orWhere('ModuleName','=', $str_search)
                                            ->orWhere('Name', 'like','%'.$str_search.'%')
                                            ->orWhere('RegistrationCode','like','%'.$str_search.'%')
                                            ->get();
            
                        return json_encode($assignment);
                    
                }
                else
                {
                    $assignment = DB::table('Assignment')
                                            ->select('Module.ModuleName as moduleName', 'Class.ClassName as className', 'Name as trainerName', 'RegistrationCode as registrationCode')
                                            ->join('Trainer', 'Assignment.TrainerID', '=', 'Trainer.UserName')
                                            ->join('Class', 'Assignment.ClassID', '=', 'Class.ClassID')
                                            ->join('Module', 'Assignment.ModuleID', '=', 'Module.ModuleID')
                                            ->where('ClassName','=' ,$str_search)
                                            ->orWhere('ModuleName','=', $str_search)
                                            ->orWhere('Name', 'like','%'.$str_search.'%')
                                            ->orWhere('RegistrationCode','like','%'.$str_search.'%')
                                            ->where('Assignment.TrainerID', '=', $idUser)
                                            ->get();
                    
                    return json_encode($assignment);
                    
                }
            }
    }

    public function addAssignment($assignment) {
        //kiểm tra trùng 
        $matchThese = ['ClassID' => $assignment['idClass'], 'ModuleID' => $assignment['idModule'], 'TrainerID' => $assignment['idTrainer']];

        $isDuplicate = Assignment::where($matchThese)
                        ->get();
        
        if(count($isDuplicate)==0)
        {
            //thêm
            $new_assignment = Assignment::create([
                'ModuleID' => $assignment['idModule'],
                'ClassID' => $assignment['idClass'],
                'TrainerID' => $assignment['idTrainer'],
                'RegistrationCode' => $assignment['registrationCode'],
            ]);

            return json_encode([
            'message' => 'Success'
            ]);
        }
        else
        {
            //trùng -> không thêm
            return json_encode([
                'message' => 'Duplicate'
             ]);
        }

    }

    public function updateAssignment($assignment) {
        //kiểm tra trùng 
        $count =0;
        Foreach($assignment as $item)
                    {
                        if($count==0)
                        {
                            $matchTheseDuplicate = ['ClassID' => $item['idClass'], 'ModuleID' => $item['idModule'], 
                                'TrainerID' => $item['idTrainer'], 'RegistrationCode' => $item['registrationCode']];
                        }
                        else
                        {
                            $matchTheseEdit = ['ClassID' => $item['idClass'], 'ModuleID' => $item['idModule'], 'TrainerID' => $item['idTrainer']];
                        }
                        $count++;
                    }
        
        $isDuplicate = Assignment::where($matchTheseDuplicate)
                        ->get();
        
        if(count($isDuplicate)==0)
        {
            $isUpdate = Assignment::where($matchTheseEdit)
                           ->update($matchTheseDuplicate);

            return json_encode([
            'message' => 'Success'
            ]);
        }
        else
        {
            //trùng -> không thêm
            return json_encode([
                'message' => 'Duplicate'
             ]);
        }
    }
    public function deleteAssignment($deleted_assignment,$isFirstStep) {

        $matchThese = ['Module.ModuleName' => $deleted_assignment['moduleName'], 'Class.ClassName' => $deleted_assignment['className'], 
                        'Name' => $deleted_assignment['trainerName'], 'Module.IsDeleted' => 0, 'Class.IsDeleted' => 0];

        $assignment = DB::table('Assignment')
                    ->select('Module.ModuleName', 'Class.ClassName', 'Name', 'RegistrationCode', 'Module.ModuleID', 'Class.ClassID', 
                            'Trainer.UserName', 'Module.IsDeleted as ModuleDeleted' , 'Class.IsDeleted as ClassDeleted')
                    ->join('Trainer', 'Assignment.TrainerID', '=', 'Trainer.UserName')
                    ->join('Class', 'Assignment.ClassID', '=', 'Class.ClassID')
                    ->join('Module', 'Assignment.ModuleID', '=', 'Module.ModuleID')
                    ->where($matchThese)
                    ->get();

            if(count($assignment)==0)
            {
                if($isFirstStep == "true")
                {
                    return json_encode([
                        'message' => 'ViolateConstraint'
                        ]);
                }
                else
                {
                    $matchThese_false = ['Module.ModuleName' => $deleted_assignment['moduleName'], 'Class.ClassName' => $deleted_assignment['className'], 
                            'Name' => $deleted_assignment['trainerName']];
    
                    $assignment_false = DB::table('Assignment')
                        ->select('Module.ModuleName', 'Class.ClassName', 'Name', 'RegistrationCode', 'Module.ModuleID', 'Class.ClassID', 
                                'Trainer.UserName', 'Module.IsDeleted as ModuleDeleted' , 'Class.IsDeleted as ClassDeleted')
                        ->join('Trainer', 'Assignment.TrainerID', '=', 'Trainer.UserName')
                        ->join('Class', 'Assignment.ClassID', '=', 'Class.ClassID')
                        ->join('Module', 'Assignment.ModuleID', '=', 'Module.ModuleID')
                        ->where($matchThese_false)
                        ->get();
    
                    //xóa
                    Foreach($assignment_false as $item)
                    {
                        $matchTheseToDelete = ['ClassID' => $item->ClassID, 'ModuleID' => $item->ModuleID, 'TrainerID' => $item->UserName];
                    }
                    
                    $isDelete = Assignment::where($matchTheseToDelete)
                           ->delete();
    
                    return json_encode([
                        'message' => 'Success'
                        ]);
                } 
            }
            //xóa
                //$matchTheseToDelete = ['ClassID' => $assignment['ClassID'], 'ModuleID' => $assignment['ModuleID'], 'TrainerID' => $assignment['UserName']];

                //xóa
                Foreach($assignment as $item)
                {
                    $matchTheseToDelete = ['ClassID' => $item->ClassID, 'ModuleID' => $item->ModuleID, 'TrainerID' => $item->UserName];
                }

                $isDelete = Assignment::where($matchTheseToDelete)
                       ->delete();
                       
                return json_encode([
                    'message' => 'Success'
                    ]);
            

        }
}