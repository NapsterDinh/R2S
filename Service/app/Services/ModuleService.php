<?php
namespace App\Services;

use App\Models\Module;
use Illuminate\Support\Facades\DB;

class ModuleService {
    public function __construct() {

    }

    public function showModule($isAll) {
        if($isAll=='true')
        {
            $module = DB::table('Module')->select(
                'ModuleID as moduleId',
                'ModuleName as moduleName',
                'StartTime as startTime',
                'EndTime as endTime',
                'IsDeleted as isDeleted',
                'AdminID as adminId',
                'FeedbackID as feedbackId',
                'FeedbackStartTime as feedbackStartTime',
                'FeedbackEndTime as feedbackEndTime'
            )
            ->get();
    
            return json_encode($module);
        }
        else
        {
            $module = DB::table('Module')->select(
                'ModuleID as moduleId',
                'ModuleName as moduleName',
                'StartTime as startTime',
                'EndTime as endTime',
                'IsDeleted as isDeleted',
                'AdminID as adminId',
                'FeedbackID as feedbackId',
                'FeedbackStartTime as feedbackStartTime',
                'FeedbackEndTime as feedbackEndTime'
            )
            ->where('IsDeleted', '=', 0)
            ->get();
    
            return json_encode($module);
        }
        
    }

    public function insertModule($data) {
        $module = Module::create([
            'ModuleID' => rand(1,1000),
            'ModuleName' => $data['moduleName'],
            'StartTime' => date("Y-m-d",strtotime($data['startTime'])),
            'EndTime' => date("Y-m-d",strtotime($data['endTime'])),
            'FeedbackID' => $data['feedbackID'],
            'FeedbackStartTime' => date("Y-m-d",strtotime($data['feedbackStartTime'])),
            'FeedbackEndTime' => date("Y-m-d",strtotime($data['feedbackEndTime'])),
            'IsDeleted' => 0,
        ]);

        return json_encode([
            'result' => $module
        ]);
    }

    public function editClass($data) {
        $module = DB::table('Module')->where('ModuleID', $data['moduleID'])
        ->update([
            'ModuleID' => rand(1,1000),
            'ModuleName' => $data['moduleName'],
            'StartTime' => date("Y-m-d",strtotime($data['startTime'])),
            'EndTime' => date("Y-m-d",strtotime($data['endTime'])),
            'FeedbackID' => $data['feedbackID'],
            'FeedbackStartTime' => date("Y-m-d",strtotime($data['feedbackStartTime'])),
            'FeedbackEndTime' => date("Y-m-d",strtotime($data['feedbackEndTime'])),
            'IsDeleted' => 0,
        ]); 

        return json_encode([
            "result" => $module
        ]);
    }

    public function deleteClass($data) {
        $module = DB::table('Module')->where('ModuleID', $data['Id'])
                    ->update([
                        'IsDeleted' => 1,
                    ]);

        return json_encode([
            "result" => $module
        ]);
    }

    public function getAll() {
        $module = DB::table('Module')->select(
            'ModuleID as moduleId',
            'ModuleName as moduleName',
            'StartTime as startTime',
            'EndTime as endTime',
            'IsDeleted as isDeleted',
            'AdminID as adminId',
            'FeedbackID as feedbackId',
            'FeedbackStartTime as feedbackStartTime',
            'FeedbackEndTime as feedbackEndTime'
        )->where('IsDeleted','=','0')
        ->get();

        return json_encode($module);
    }
}